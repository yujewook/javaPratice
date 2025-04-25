package FileConVert;

import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PngOcrToPdfConverter {

    public void run(String inputImagePath, String outputPdfPath) {
        try {
            // 확장자 확인
            String lowerPath = inputImagePath.toLowerCase();
            if (!(lowerPath.endsWith(".png") || lowerPath.endsWith(".jpg") || lowerPath.endsWith(".jpeg"))) {
                throw new IllegalArgumentException("지원하지 않는 이미지 형식입니다. PNG 또는 JPG만 지원합니다.");
            }

            // 1. Tesseract 설정
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            tesseract.setLanguage("eng+kor");

            // 2. OCR 수행
            String ocrText = tesseract.doOCR(new File(inputImagePath));

            // 3. PDF 생성
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);

            for (String line : ocrText.split("\n")) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();
            contentStream.close();

            document.save(outputPdfPath);
            document.close();

            System.out.println("✅ 이미지 OCR → PDF 변환 완료: " + outputPdfPath);
        } catch (Exception e) {
            System.err.println("이미지 OCR → PDF 변환 중 오류 발생: " + e.getMessage());
        }
    }
}
