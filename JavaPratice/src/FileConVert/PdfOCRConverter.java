package FileConVert;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PdfOCRConverter {
    private static final String TESSERACT_CMD = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe";
    private static final String LANG = "eng+kor";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.println("OCR을 적용할 PDF 파일이 있는 폴더 경로를 입력하세요: ");
        String inputFolderPath = scanner.nextLine().trim();
        System.out.println(inputFolderPath);

        System.out.println("OCR 변환된 PDF가 저장될 폴더 경로를 입력하세요: ");
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

        Arrays.sort(pdfFiles, Comparator.comparingInt(PdfOCRConverter::extractNumberFromFileName));

        for (File pdfFile : pdfFiles) {
            applyOCRToPDF(pdfFile, outputFolderPath);
        }
        System.out.println("모든 PDF 파일에 OCR 변환이 완료되었습니다.");
        long end = System.currentTimeMillis();
        
        System.out.println("실행 시간: " + (end - start) + "ms");
    }

    private static int extractNumberFromFileName(File file) {
        String name = file.getName().replaceAll("[^0-9]", ""); // 숫자만 추출
        try {
            return Integer.parseInt(name);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // 숫자가 없으면 가장 뒤로 정렬
        }
    }

    private static void applyOCRToPDF(File pdfFile, String outputFolderPath) {
        String fileName = pdfFile.getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        String outputFilePath = outputFolderPath + File.separator + "ocr_" + baseName + ".pdf";

        try (PDDocument document = PDDocument.load(pdfFile);
             PDDocument ocrDocument = new PDDocument()) {

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(i, 300);
                File tempImageFile = File.createTempFile("ocr_page_" + i, ".png");
                ImageIO.write(image, "png", tempImageFile);

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
                process.waitFor();

                File ocrResultFile = new File(outputBase + ".pdf");
                if (ocrResultFile.exists()) {
                    try (PDDocument ocrPage = PDDocument.load(ocrResultFile)) {
                        PDFMergerUtility merger = new PDFMergerUtility();
                        merger.appendDocument(ocrDocument, ocrPage);
                    }
                    ocrResultFile.delete();
                } else {
                    System.err.println("OCR 변환된 PDF를 찾을 수 없습니다: 페이지 " + (i + 1));
                }

                tempImageFile.delete();
            }

            if (ocrDocument.getNumberOfPages() > 0) {
                ocrDocument.save(outputFilePath);
                System.out.println("OCR 변환 완료: " + outputFilePath);
            } else {
                System.err.println("OCR 변환에 실패하였습니다: " + pdfFile.getName());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("파일 변환 중 오류 발생: " + e.getMessage());
        }
    }
}
