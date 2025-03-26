package FileConVert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class JpgToPDF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 사용자로부터 입력 경로 받기
        System.out.print("JPG 파일이 있는 폴더 경로를 입력하세요: ");
        String inputDir = scanner.nextLine().trim();

        System.out.print("PDF 파일을 저장할 폴더 경로를 입력하세요: ");
        String outputDir = scanner.nextLine().trim();

        scanner.close();

        // 입력 폴더 유효성 검사
        File folder = new File(inputDir);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("유효한 폴더 경로가 아닙니다.");
            return;
        }

        // JPG -> PDF 변환 실행
        try {
            convertJpgToPdf(inputDir, outputDir);
            System.out.println("모든 JPG 파일이 PDF로 변환되었습니다!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertJpgToPdf(String inputDir, String outputDir) throws IOException {
        File folder = new File(inputDir);
        File[] imageFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("JPG 파일이 없습니다.");
            return;
        }

        for (File imageFile : imageFiles) {
            String baseName = imageFile.getName().replaceAll("(?i)\\.jpg$", "");
            String outputPdf = outputDir + "/" + "친절한 SQL"+ baseName + ".pdf";

            try (PDDocument document = new PDDocument()) {
                BufferedImage image = ImageIO.read(imageFile);
                PDImageXObject pdImage = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);

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
