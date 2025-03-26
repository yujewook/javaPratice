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

        // ����ڷκ��� �Է� ��� �ޱ�
        System.out.print("JPG ������ �ִ� ���� ��θ� �Է��ϼ���: ");
        String inputDir = scanner.nextLine().trim();

        System.out.print("PDF ������ ������ ���� ��θ� �Է��ϼ���: ");
        String outputDir = scanner.nextLine().trim();

        scanner.close();

        // �Է� ���� ��ȿ�� �˻�
        File folder = new File(inputDir);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("��ȿ�� ���� ��ΰ� �ƴմϴ�.");
            return;
        }

        // JPG -> PDF ��ȯ ����
        try {
            convertJpgToPdf(inputDir, outputDir);
            System.out.println("��� JPG ������ PDF�� ��ȯ�Ǿ����ϴ�!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertJpgToPdf(String inputDir, String outputDir) throws IOException {
        File folder = new File(inputDir);
        File[] imageFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("JPG ������ �����ϴ�.");
            return;
        }

        for (File imageFile : imageFiles) {
            String baseName = imageFile.getName().replaceAll("(?i)\\.jpg$", "");
            String outputPdf = outputDir + "/" + "ģ���� SQL"+ baseName + ".pdf";

            try (PDDocument document = new PDDocument()) {
                BufferedImage image = ImageIO.read(imageFile);
                PDImageXObject pdImage = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);

                PDPage page = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.drawImage(pdImage, 0, 0, image.getWidth(), image.getHeight());
                }

                document.save(outputPdf);
                System.out.println("��ȯ �Ϸ�: " + outputPdf);
            }
        }
    }

}
