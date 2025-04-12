package FileConVert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class JpgToPDF {

    public void convert(String inputDir, String outputDir) throws IOException {
        File folder = new File(inputDir);
        File[] imageFiles = folder.listFiles(file -> {
            try {
                return file.isFile() && file.getName().toLowerCase().matches(".*\\.(jpg|jpeg)");
            } catch (Exception e) {
                System.err.println("파일 필터 중 오류 발생: " + e.getMessage());
                return false;
            }
        });

        System.out.println("입력 경로 확인: " + inputDir);
        System.out.println("파일 존재 여부: " + folder.exists());
        System.out.println("디렉토리 여부: " + folder.isDirectory());
        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("JPG 파일이 없습니다. 경로: " + folder.getAbsolutePath());
            return;
        }

        for (File imageFile : imageFiles) {
            String baseName = imageFile.getName().replaceAll("(?i)\\.jpe?g$", "");
            String outputPdf = outputDir + File.separator + baseName + ".pdf";

            try (PDDocument document = new PDDocument()) {
                BufferedImage image = ImageIO.read(imageFile);
                PDImageXObject pdImage = PDImageXObject.createFromFileByContent(imageFile, document);

                PDPage page = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.drawImage(pdImage, 0, 0, image.getWidth(), image.getHeight());
                }

                document.save(outputPdf);
                System.out.println("변환 완료: " + outputPdf);
            }
        }
    }
}
