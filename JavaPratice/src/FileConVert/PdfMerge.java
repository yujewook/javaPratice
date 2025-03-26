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

        // 사용자 입력 받기
        System.out.print("PDF 파일들이 있는 폴더 경로를 입력하세요: ");
        String inputDir = scanner.nextLine().trim();

        System.out.print("출력할 병합된 PDF 파일 경로를 입력하세요 (예: C:/output/final.pdf): ");
        String outputPdfPath = scanner.nextLine().trim();

        scanner.close();

        // 입력 폴더 검사
        File folder = new File(inputDir);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("유효한 폴더 경로가 아닙니다.");
            return;
        }

        // 병합 실행
        try {
            mergePdfFiles(inputDir, outputPdfPath);
            System.out.println("모든 PDF 파일이 하나의 PDF로 병합되었습니다! (" + outputPdfPath + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mergePdfFiles(String inputDir, String outputPdfPath) throws IOException {
        File folder = new File(inputDir);
        File[] pdfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles == null || pdfFiles.length == 0) {
            System.out.println("PDF 파일이 없습니다.");
            return;
        }

        // 가장 큰 페이지 크기 구하기
        PDRectangle maxSize = new PDRectangle(0, 0);
        for (File pdfFile : pdfFiles) {
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                PDPage page = doc.getPage(0);  // 첫 페이지만 크기 확인
                PDRectangle mediaBox = page.getMediaBox();
                maxSize.setLowerLeftX(0);
                maxSize.setLowerLeftY(0);
                maxSize.setUpperRightX(Math.max(maxSize.getUpperRightX(), mediaBox.getWidth()));
                maxSize.setUpperRightY(Math.max(maxSize.getUpperRightY(), mediaBox.getHeight()));
            }
        }

        // 파일 이름에서 숫자 부분 추출 후 정렬
        Arrays.sort(pdfFiles, Comparator.comparingInt(PdfMerge::extractNumber));

        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(outputPdfPath);

        for (File pdfFile : pdfFiles) {
            try (PDDocument doc = PDDocument.load(pdfFile)) {
                // 각 PDF 파일의 크기를 가장 큰 크기에 맞추기
                for (PDPage page : doc.getPages()) {
                    PDRectangle mediaBox = page.getMediaBox();
                    if (mediaBox.getWidth() != maxSize.getWidth() || mediaBox.getHeight() != maxSize.getHeight()) {
                        page.setMediaBox(maxSize);  // 페이지 크기 조정
                    }
                }
                // 병합할 때 파일 추가
                merger.addSource(pdfFile);
                System.out.println("추가: " + pdfFile.getName());
            }
        }

        // 병합 실행
        merger.mergeDocuments(null);
    }

    // 파일명에서 숫자 추출하는 메서드
    private static int extractNumber(File file) {
        String name = file.getName().replaceAll("[^0-9]", ""); // 숫자만 추출
        try {
            return Integer.parseInt(name); // 숫자로 변환
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE; // 숫자가 없으면 맨 뒤로 정렬
        }
    }
}