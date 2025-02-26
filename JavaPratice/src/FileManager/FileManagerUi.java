package FileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class FileManagerUi {
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("엑셀 및 TXT 파일 관리 프로그램 시작");
        FileManagerUtil fmu = new FileManagerUtil();
        String inputFilePath;
        String outputDirPath;
        String directory;
        ArrayList<String> txtData;
        ArrayList<FileDataDTO> excelData;

        while (true) {
            System.out.print("검색할 디렉토리 경로 입력 (예: D:/output/): ");
            directory = scanner.nextLine();
            fmu.searchDirectory(directory);

            System.out.print("원본 파일 경로 입력 (예: D:/test.xlsx 또는 D:/test.txt): ");
            inputFilePath = scanner.nextLine();

            System.out.println("\n========= 메뉴 선택 =========");
            System.out.println("1. 엑셀/TXT 파일 읽기");
            System.out.println("2. 파일 복사");
            System.out.println("3. 파일 정렬 후 복사");
            System.out.println("4. 이자계산파일");
            System.out.println("5. 종료");
            System.out.print("선택 (1~5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (inputFilePath.endsWith(".xlsx")) {
                        fmu.readExcelFile(inputFilePath);
                    } else if (inputFilePath.endsWith(".txt")) {
                        fmu.readTxtFile(inputFilePath);
                    } else {
                        System.out.println("지원되지 않는 파일 형식입니다.");
                    }
                    break;
                case 2:
                    System.out.print("복사할 디렉토리 경로 입력 (예: D:/output/): ");
                    outputDirPath = scanner.nextLine();
                    fmu.createDirectory(outputDirPath);
                    fmu.copyFile(inputFilePath, outputDirPath);
                    break;
                case 3:
                    System.out.print("복사할 파일명과 경로 입력 (예: D:/output/sorted.xlsx 또는 D:/output/sorted.txt): ");
                    outputDirPath = scanner.nextLine();
                    
                    if (inputFilePath.endsWith(".xlsx")) {
                        excelData = fmu.sortFile(inputFilePath);
                        fmu.copySortExcelFile(excelData, outputDirPath , inputFilePath);
                    } else if (inputFilePath.endsWith(".txt")) {
                        txtData = fmu.sortTxtFile(inputFilePath);
                        fmu.copySortTxtFile(txtData, outputDirPath);
                    } else {
                        System.out.println("지원되지 않는 파일 형식입니다.");
                    }
                    break;
                case 4:
                	System.out.println("입금데이터엑셀 파일명과 경로 입력 (예: D:/test.xlsx)");
                	String incomefileName = scanner.nextLine();
                	System.out.println("이자데이터엑셀 파일명과 경로 입력 (예: D:/test1.xlsx)");
                	String ratefileName = scanner.nextLine();
                	fmu.rateCalFile(incomefileName , ratefileName);
                    return;
                case 5:
                	System.out.println("프로그램 종료.");
                	return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }
    
    
}
