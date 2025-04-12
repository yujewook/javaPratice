package FileConVert;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public final class PdfMergeUtil {

    PdfMergeUtil() {}

    public void merge(String inputDir, String outputPdfPath) throws IOException {
        File folder = new File(inputDir);
        File[] pdfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles == null || pdfFiles.length == 0) {
            System.out.println("PDF 파일이 없습니다. 경로: " + folder.getAbsolutePath());
            return;
        }

        PDRectangle maxSize = new PDRectangle(0, 0);
        for (File pdfFile : pdfFiles) {
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                PDPage page = doc.getPage(0);
                PDRectangle mediaBox = page.getMediaBox();
                maxSize.setLowerLeftX(0);
                maxSize.setLowerLeftY(0);
                maxSize.setUpperRightX(Math.max(maxSize.getUpperRightX(), mediaBox.getWidth()));
                maxSize.setUpperRightY(Math.max(maxSize.getUpperRightY(), mediaBox.getHeight()));
            }
        }

        Arrays.sort(pdfFiles, Comparator.comparingInt(PdfMergeUtil::extractNumber));

        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(outputPdfPath);

        for (File pdfFile : pdfFiles) {
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                for (PDPage page : doc.getPages()) {
                    PDRectangle mediaBox = page.getMediaBox();
                    if (mediaBox.getWidth() != maxSize.getWidth() || mediaBox.getHeight() != maxSize.getHeight()) {
                        page.setMediaBox(maxSize);
                    }
                }
                merger.addSource(pdfFile);
                System.out.println("추가: " + pdfFile.getName());
            }
        }

        merger.mergeDocuments(null);
        System.out.println("✅ 모든 PDF 병합 완료: " + outputPdfPath);
    }

    private static int extractNumber(File file) {
        String name = file.getName().replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}
