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

        System.out.println("OCR를 적용할 PDF 파일이 있는 폴더 경로를 입력하세요: ");
        String inputFolderPath = scanner.nextLine().trim();

        System.out.println("OCR 변환된 PDF를 저장할 폴더 경로를 입력하세요: ");
        String outputFolderPath = scanner.nextLine().trim();

        File inputFolder = new File(inputFolderPath);
        if (!inputFolder.exists() || !inputFolder.isDirectory()) {
            System.err.println("입력 폴더가 존재하지 않거나 유효하지 않습니다.");
            return;
        }

        File[] pdfFiles = inputFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
        if (pdfFiles == null || pdfFiles.length == 0) {
            System.err.println("PDF 파일이 존재하지 않습니다.");
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
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 300);
            File tempImageFile = File.createTempFile("ocr_page_" + pageIndex, ".png");
            ImageIO.write(image, "png", tempImageFile);

            File tempOutputFile = File.createTempFile("ocr_output_" + pageIndex, ".pdf");
            String outputBase = tempOutputFile.getAbsolutePath().replaceFirst("\\.pdf$", "");

            ProcessBuilder pb = new ProcessBuilder(
                    TESSERACT_CMD,
                    tempImageFile.getAbsolutePath(),
                    outputBase,
                    "-l", LANG,
                    "pdf"
            );
            pb.redirectErrorStream(true);
            pb.start().waitFor();

            File ocrResultFile = new File(outputBase + ".pdf");
            if (ocrResultFile.exists()) {
                tempPdfFiles.add(ocrResultFile);
            } else {
                System.err.println("OCR 변환된 PDF를 찾을 수 없습니다: 페이지 " + (pageIndex + 1));
            }
            tempImageFile.delete();
        } catch (Exception e) {
            System.err.println("페이지 OCR 변환 중 오류 발생: " + e.getMessage());
        }
    }
}