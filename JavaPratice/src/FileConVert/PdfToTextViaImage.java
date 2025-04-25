package FileConVert;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;

public class PdfToTextViaImage {
    private static final String TESSERACT_CMD = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe";
    private static final String PDFTOPPM_CMD = "C:\\Program Files\\poppler-24.08.0\\Library\\bin\\pdftoppm.exe"; // pdftoppm이 환경변수에 포함되어 있어야 함
    private static final String LANG = "eng+kor";
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public void run(String inputPdfPath, String outputBasePath) {
        try {
            // 입력 PDF 존재 여부 확인
            File inputFile = new File(inputPdfPath);
            if (!inputFile.exists()) {
                System.err.println("❌ 입력 PDF 파일이 존재하지 않음: " + inputPdfPath);
                return;
            }

            // 출력 이미지 디렉토리 설정 (예: D:/output/파일1/파일명output_images)
            String imageDirPath = outputBasePath.replaceAll("\\.pdf$", "") + "output_images";
            File imageDir = new File(imageDirPath);
            imageDir.mkdirs();

            // 출력 이미지 prefix 경로 (예: D:/output/파일1/파일명output_images/page)
            String imagePrefixPath = imageDir.getAbsolutePath() + File.separator + "page";

            // pdftoppm 프로세스 실행
            ProcessBuilder pdfToImg = new ProcessBuilder(
                PDFTOPPM_CMD,
                "-r", "300",
                "-png",
                inputPdfPath,
                imagePrefixPath
            );
            pdfToImg.redirectErrorStream(true);
            Process p1 = pdfToImg.start();

            // 실행 로그 출력
            BufferedReader reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[pdftoppm] " + line);
            }

            // 프로세스 종료 대기
            int exitCode = p1.waitFor();
            System.out.println("pdftoppm 종료 코드: " + exitCode);

            // 2. PNG 파일 리스트 수집
            File[] pngFiles = imageDir.listFiles((dir, name) -> name.endsWith(".png"));
            if (pngFiles == null || pngFiles.length == 0) {
                System.err.println("이미지 변환 실패 또는 이미지 없음.");
                return;
            }
            Arrays.sort(pngFiles, Comparator.comparing(File::getName)); // 순서 정렬

            // 3. 병렬 OCR 수행
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            List<Future<String>> futures = new ArrayList<>();

            for (File image : pngFiles) {
                futures.add(executor.submit(() -> runTesseract(image)));
            }

            executor.shutdown();
            executor.awaitTermination(30, TimeUnit.MINUTES);
            
            String originalPath = outputBasePath; // 예: "C:/work/파일명.pdf"
            String txtPath = originalPath.replaceAll("\\.pdf$", ".txt");
            // 4. 텍스트 저장
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtPath))) {
                int pageIndex = 1;
                for (Future<String> future : futures) {
                    String text = future.get();
                    writer.write("---- Page " + (pageIndex++) + " ----\n");
                    writer.write(text);
                    writer.write("\n\n");
                }
                System.out.println("✅ 텍스트 OCR 저장 완료: " + outputBasePath + ".txt");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String runTesseract(File imageFile) throws IOException, InterruptedException {
        File txtFile = new File(imageFile.getAbsolutePath().replace(".png", ""));
        ProcessBuilder pb = new ProcessBuilder(
            TESSERACT_CMD,
            imageFile.getAbsolutePath(),
            txtFile.getAbsolutePath(),
            "-l", LANG
        );
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor();

        File outTxt = new File(txtFile.getAbsolutePath() + ".txt");
        if (outTxt.exists()) {
            return new String(Files.readAllBytes(outTxt.toPath()));
        }
        return "[ERROR] OCR 실패";
    }

}
