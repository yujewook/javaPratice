package FileConVert;

import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class PdfUiManager {
	private final Scanner scanner = new Scanner(System.in, Charset.forName("MS949"));



    public void run() {
    	while (true) {
		    System.out.println("========== PDF 변환 유틸리티 ==========");
		    System.out.println("1. JPG → PDF 변환");
		    System.out.println("2. PDF 병합");
		    System.out.println("3. OCR 적용 (단일 스레드)");
		    System.out.println("4. OCR 적용 (멀티 스레드)");
		    System.out.println("5. 종료");
            System.out.print("메뉴를 선택하세요 (1~5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> runJpgToPdf();
                case "2" -> runPdfMerge();
                case "3" -> runPdfOcrSingle();
                case "4" -> runPdfOcrMulti();
                case "5" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 입력입니다. 1~5 사이의 숫자를 입력해주세요.");
            }
        }
    }

    private void runJpgToPdf() {
	    System.out.print("JPG 파일이 있는 폴더 경로: ");
	    String inputDir = scanner.nextLine().trim();
	    System.out.print("PDF 저장 경로: ");
	    String outputDir = scanner.nextLine().trim();
	
	    try {
	        JpgToPDF converter = new JpgToPDF();
	        converter.convert(inputDir, outputDir);
	        System.out.println("✅ JPG → PDF 변환 완료");
	    } catch (Exception e) {
	        System.err.println("JPG → PDF 변환 중 오류 발생: " + e.getMessage());
	    }

    } 

    private void runPdfMerge() {
        System.out.print("PDF 파일들이 있는 폴더 경로: ");
        String inputDir = scanner.nextLine().trim();
        System.out.print("병합된 PDF 파일 경로를 포함해 입력하세요 (예: C:/output/merged.pdf): ");
        String outputPath = scanner.nextLine().trim();

        try {
        	PdfMergeUtil PdfMerge = new PdfMergeUtil(); 
            PdfMerge.merge(inputDir, outputPath);
            System.out.println("✅ PDF 병합 완료");
        } catch (Exception e) {
            System.err.println("PDF 병합 중 오류 발생: " + e.getMessage());
        }
    }

    private void runPdfOcrSingle() {
        System.out.println("OCR 적용할 PDF 폴더 경로: ");
        String inputPath = scanner.nextLine().trim();
        System.out.println("OCR 결과 저장 폴더 경로: ");
        String outputPath = scanner.nextLine().trim();

        try {
        	PdfOCRConverter converter = new PdfOCRConverter();
        	converter.run(inputPath, outputPath);
        } catch (Exception e) {
            System.err.println("OCR 변환 중 오류 발생: " + e.getMessage());
        }
    }

    private void runPdfOcrMulti() {
        System.out.println("OCR 적용할 PDF 폴더 경로: ");
        String inputPath = scanner.nextLine().trim();
        System.out.println("OCR 결과 저장 폴더 경로: ");
        String outputPath = scanner.nextLine().trim();

        try {
            ThreadConvertOcr.main(new String[]{inputPath, outputPath});
        } catch (Exception e) {
            System.err.println("멀티스레드 OCR 변환 중 오류 발생: " + e.getMessage());
        }
    }
}