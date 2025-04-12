package FileConVert;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class PdfOCRConverterMultiThreaded {
    private static final String TESSERACT_CMD = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe";
    private static final String LANG = "eng+kor";
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public void run(String inputPath, String outputPath) {
        try (PDDocument document = PDDocument.load(new File(inputPath))) {
            int pageCount = document.getNumberOfPages();
            PDFRenderer renderer = new PDFRenderer(document);
            ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
            List<Future<PageOCRResult>> futures = new ArrayList<>();

            for (int i = 0; i < pageCount; i++) {
                final int pageIndex = i;
                futures.add(executor.submit(() -> processPage(document, renderer, pageIndex)));
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.HOURS);

            List<PageOCRResult> results = new ArrayList<>();
            for (Future<PageOCRResult> future : futures) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    System.err.println("페이지 OCR 처리 실패: " + e.getMessage());
                }
            }

            results.sort(Comparator.comparingInt(r -> r.pageIndex));

            try (PDDocument finalDoc = new PDDocument()) {
                for (PageOCRResult result : results) {
                    if (result.document != null) {
                        PDFMergerUtility merger = new PDFMergerUtility();
                        merger.appendDocument(finalDoc, result.document);
                        result.document.close();
                    } else {
                        finalDoc.addPage(new PDPage());
                    }
                }
                finalDoc.save(outputPath);
                System.out.println("✅ OCR 완료: " + outputPath);
            }

        } catch (Exception e) {
            System.err.println("파일 전체 처리 중 오류: " + e.getMessage());
        }
    }

    private PageOCRResult processPage(PDDocument document, PDFRenderer renderer, int pageIndex) {
        try {
            BufferedImage image = renderer.renderImageWithDPI(pageIndex, 300);
            File tempImageFile = File.createTempFile("page_" + pageIndex, ".png");
            ImageIO.write(image, "png", tempImageFile);

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
                System.err.println("❌ 타임아웃 발생: 페이지 " + (pageIndex + 1));
                return new PageOCRResult(pageIndex, null);
            }

            File ocrResultFile = new File(outputBase + ".pdf");
            if (ocrResultFile.exists()) {
                PDDocument resultDoc = PDDocument.load(ocrResultFile);
                return new PageOCRResult(pageIndex, resultDoc);
            }

        } catch (Throwable t) {
            System.err.println("❌ 페이지 " + (pageIndex + 1) + " 처리 오류: " + t.getMessage());
        }
        return new PageOCRResult(pageIndex, null);
    }

    static class PageOCRResult {
        int pageIndex;
        PDDocument document;

        PageOCRResult(int pageIndex, PDDocument document) {
            this.pageIndex = pageIndex;
            this.document = document;
        }
    }
}