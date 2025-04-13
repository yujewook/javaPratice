package FileConVert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PdfOCRConverter {
    private static final String TESSERACT_CMD = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe";
    private static final String LANG = "eng+kor";

    public void run(String inputFolderPath, String outputFolderPath) {
        long start = System.currentTimeMillis();

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

        Arrays.sort(pdfFiles, Comparator.comparingInt(this::extractNumberFromFileName));

        for (File pdfFile : pdfFiles) {
            try {
                applyOCRToPDF(pdfFile, outputFolderPath);
            } catch (Throwable t) {
                System.err.println("⚠️ 파일 처리 중 오류 발생, 건너뜀: " + pdfFile.getName());
                t.printStackTrace();
            }
        }
        System.out.println("모든 PDF 파일에 OCR 변환이 완료되었습니다.");
        long end = System.currentTimeMillis();

        System.out.println("실행 시간: " + (end - start) + "ms");
    }

    private int extractNumberFromFileName(File file) {
        String name = file.getName().replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    private void applyOCRToPDF(File pdfFile, String outputFolderPath) {
        String fileName = pdfFile.getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        String outputFilePath = outputFolderPath + File.separator + "ocr_" + baseName + ".pdf";

        try (PDDocument document = PDDocument.load(pdfFile);
             PDDocument ocrDocument = new PDDocument()) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                try {
                    BufferedImage image = pdfRenderer.renderImageWithDPI(i, 150);
                    File tempImageFile = File.createTempFile("ocr_page_" + i, ".jpg");
                    ImageIO.write(image, "jpg", tempImageFile);

                    File tempOutputFile = File.createTempFile("ocr_output_" + i, ".txt");
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
                    boolean finished = process.waitFor(3, java.util.concurrent.TimeUnit.MINUTES);
                    if (!finished) {
                        process.destroy();
                        System.err.println("❌ OCR 처리 시간이 너무 오래 걸립니다 (타임아웃). 페이지: " + (i + 1));
                        ocrDocument.addPage(new PDPage());
                        continue;
                    }

                    File ocrResultFile = new File(outputBase + ".pdf");
                    if (ocrResultFile.exists()) {
                        try (PDDocument ocrPage = PDDocument.load(ocrResultFile)) {
                            PDFMergerUtility merger = new PDFMergerUtility();
                            merger.appendDocument(ocrDocument, ocrPage);
                        }
                        ocrResultFile.deleteOnExit();
                    } else {
                        System.err.println("OCR 변환된 PDF를 찾을 수 없습니다: 페이지 " + (i + 1));
                        ocrDocument.addPage(new PDPage());
                    }

                    tempImageFile.deleteOnExit();
                } catch (Throwable t) {
                    System.err.println("⚠️ 페이지 " + (i + 1) + " 처리 중 오류 발생: " + t.getMessage());
                    ocrDocument.addPage(new PDPage());
                }
            }

            if (ocrDocument.getNumberOfPages() > 0) {
                ocrDocument.save(outputFilePath);
                System.out.println("OCR 변환 완료: " + outputFilePath);
            } else {
                System.err.println("OCR 변환에 실패하였습니다: " + pdfFile.getName());
            }

        } catch (Exception e) {
            System.err.println("파일 변환 중 오류 발생: " + e.getMessage());
        }
    }
}
