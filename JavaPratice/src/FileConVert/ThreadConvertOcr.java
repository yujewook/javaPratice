package FileConVert;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

public class ThreadConvertOcr {
    private static final String TESSERACT_CMD = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe";
    private static final String LANG = "eng+kor";
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.println("PDF filedir : ex D:\\output  ");
        String inputFolderPath = scanner.nextLine().trim();

        System.out.println("save dir : ex D:\\output  ");
        String outputFolderPath = scanner.nextLine().trim();

        File inputFolder = new File(inputFolderPath);
        if (!inputFolder.exists() || !inputFolder.isDirectory()) {
            System.err.println("notvalided");
            return;
        }

        File[] pdfFiles = inputFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
        if (pdfFiles == null || pdfFiles.length == 0) {
            System.err.println("PDF not extist");
            return;
        }

        Arrays.sort(pdfFiles, Comparator.comparingInt(ThreadConvertOcr::extractNumberFromFileName));

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<Void>> futures = new ArrayList<>();

        for (File pdfFile : pdfFiles) {
            futures.add(executor.submit(() -> {
                applyOCRToPDF(pdfFile, outputFolderPath);
                return null;
            }));
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                System.err.println("OCR 변환 중 오류 발생: " + e.getMessage());
            }
        }

        executor.shutdown();
        System.out.println("모든 PDF 파일의 OCR 변환이 완료되었습니다.");
        long end = System.currentTimeMillis();
        System.out.println("소요 시간: " + (end - start) + "ms");
    }

    private static int extractNumberFromFileName(File file) {
        String name = file.getName().replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    private static void applyOCRToPDF(File pdfFile, String outputFolderPath) {
        String fileName = pdfFile.getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        String outputFilePath = outputFolderPath + File.separator + "ocr_" + baseName + ".pdf";

        try (PDDocument document = PDDocument.load(pdfFile);
             PDDocument ocrDocument = new PDDocument()) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);
            ConcurrentLinkedQueue<File> tempPdfFiles = new ConcurrentLinkedQueue<>();

            ExecutorService pageExecutor = Executors.newFixedThreadPool(THREAD_COUNT);
            List<Future<Void>> pageFutures = new ArrayList<>();

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                final int pageIndex = i;
                pageFutures.add(pageExecutor.submit(() -> {
                    processPageOCR(pdfRenderer, pageIndex, tempPdfFiles);
                    return null;
                }));
            }

            for (Future<Void> future : pageFutures) {
                try {
                    future.get();
                } catch (Exception e) {
                    System.err.println("페이지 OCR 변환 중 오류 발생: " + e.getMessage());
                }
            }

            pageExecutor.shutdown();

            PDFMergerUtility merger = new PDFMergerUtility();
            for (File tempPdf : tempPdfFiles) {
                try (PDDocument tempDoc = PDDocument.load(tempPdf)) {
                    merger.appendDocument(ocrDocument, tempDoc);
                }
                tempPdf.delete();
            }

            if (ocrDocument.getNumberOfPages() > 0) {
                ocrDocument.save(outputFilePath);
                System.out.println("OCR 변환 완료: " + outputFilePath);
            } else {
                System.err.println("OCR 변환 실패: " + pdfFile.getName());
            }
        } catch (IOException e) {
            System.err.println("파일 변환 중 오류 발생: " + e.getMessage());
        }
    }

    private static void processPageOCR(PDFRenderer pdfRenderer, int pageIndex, ConcurrentLinkedQueue<File> tempPdfFiles) {
        try {
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 150); // 용량 줄이기
            File tempImageFile = File.createTempFile("ocr_page_" + pageIndex, ".jpg"); // JPG로 저장
            ImageIO.write(image, "jpg", tempImageFile);

            File tempOutputFile = File.createTempFile("ocr_output_" + pageIndex, ".txt");
            String outputBase = tempOutputFile.getAbsolutePath().replaceFirst("\\.txt$", "");

            ProcessBuilder pb = new ProcessBuilder(
                    TESSERACT_CMD,
                    tempImageFile.getAbsolutePath(),
                    outputBase,
                    "-l", LANG,
                    "pdf"
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();

            boolean finished = process.waitFor(3, TimeUnit.MINUTES);
            if (!finished) {
                process.destroy();
                System.err.println("⏰ OCR 타임아웃 발생 (페이지 " + pageIndex + ")");
                return;
            }

            File ocrResultFile = new File(outputBase + ".pdf");
            if (ocrResultFile.exists()) {
                tempPdfFiles.add(ocrResultFile);
            } else {
                System.err.println("OCR 변환된 PDF를 찾을 수 없습니다: 페이지 " + (pageIndex + 1));
            }

            tempImageFile.deleteOnExit();  // 변환 후 자동 삭제
            tempOutputFile.deleteOnExit();

        } catch (Exception e) {
            System.err.println("페이지 OCR 변환 중 오류 발생: " + e.getMessage());
        }
    }
}