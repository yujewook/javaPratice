package FileConVert;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PdfMerge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ����� �Է� �ޱ�
        System.out.print("PDF ���ϵ��� �ִ� ���� ��θ� �Է��ϼ���: ");
        String inputDir = scanner.nextLine().trim();

        System.out.print("����� ���յ� PDF ���� ��θ� �Է��ϼ��� (��: C:/output/final.pdf): ");
        String outputPdfPath = scanner.nextLine().trim();

        scanner.close();

        // �Է� ���� �˻�
        File folder = new File(inputDir);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("��ȿ�� ���� ��ΰ� �ƴմϴ�.");
            return;
        }

        // ���� ����
        try {
            mergePdfFiles(inputDir, outputPdfPath);
            System.out.println("��� PDF ������ �ϳ��� PDF�� ���յǾ����ϴ�! (" + outputPdfPath + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergePdfFiles(String inputDir, String outputPdfPath) throws IOException {
        File folder = new File(inputDir);
        File[] pdfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles == null || pdfFiles.length == 0) {
            System.out.println("PDF ������ �����ϴ�.");
            return;
        }

        // ���� ū ������ ũ�� ���ϱ�
        PDRectangle maxSize = new PDRectangle(0, 0);
        for (File pdfFile : pdfFiles) {
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                PDPage page = doc.getPage(0);  // ù �������� ũ�� Ȯ��
                PDRectangle mediaBox = page.getMediaBox();
                maxSize.setLowerLeftX(0);
                maxSize.setLowerLeftY(0);
                maxSize.setUpperRightX(Math.max(maxSize.getUpperRightX(), mediaBox.getWidth()));
                maxSize.setUpperRightY(Math.max(maxSize.getUpperRightY(), mediaBox.getHeight()));
            }
        }

        // ���� �̸����� ���� �κ� ���� �� ����
        Arrays.sort(pdfFiles, Comparator.comparingInt(PdfMerge::extractNumber));

        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(outputPdfPath);

        for (File pdfFile : pdfFiles) {
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                // �� PDF ������ ũ�⸦ ���� ū ũ�⿡ ���߱�
                for (PDPage page : doc.getPages()) {
                    PDRectangle mediaBox = page.getMediaBox();
                    if (mediaBox.getWidth() != maxSize.getWidth() || mediaBox.getHeight() != maxSize.getHeight()) {
                        page.setMediaBox(maxSize);  // ������ ũ�� ����
                    }
                }
                // ������ �� ���� �߰�
                merger.addSource(pdfFile);
                System.out.println("�߰�: " + pdfFile.getName());
            }
        }

        // ���� ����
        merger.mergeDocuments(null);
    }

    // ���ϸ��� ���� �����ϴ� �޼���
    private static int extractNumber(File file) {
        String name = file.getName().replaceAll("[^0-9]", ""); // ���ڸ� ����
        try {
            return Integer.parseInt(name); // ���ڷ� ��ȯ
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // ���ڰ� ������ �� �ڷ� ����
        }
    }
}