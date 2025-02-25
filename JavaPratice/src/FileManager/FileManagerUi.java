package FileManager;

import java.util.Scanner;

public class FileManagerUi {
    // 프로그램 실행 (메뉴 선택)
    public void run() throws Exception {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("엑셀 파일 관리 프로그램 시작");
        FileManagerUtil fmu = new FileManagerUtil(); 
        String inputFilePath ;
        String outputDirPath;
        String directory;
        
        while (true) {
        	//1.디렉토리 조회
        	System.out.print("검색할 디렉토리 경로 입력 (예: D:/output/): ");
        	directory = scanner.nextLine();
        	fmu.searchDirectory(directory);
        	//2.원본파일명 경로 입력        	
        	System.out.print("원본 엑셀 파일 경로 입력 (예: D:/test.xlsx): ");
        	inputFilePath = scanner.nextLine();

        	
        	System.out.println("\n========= 메뉴 선택 =========");
            System.out.println("1. 엑셀 파일 읽기");
            System.out.println("2. 엑셀 파일 복사");
            System.out.println("3. 엑셀 파일 sorting 후 해당 파일에 저장");
            System.out.println("4. 엑셀 파일 sorting 후 복사 파일에 저장");
            System.out.println("5. 고객Data 이자Data 적용"); 
            System.out.println("6. 종료");
            System.out.print("선택 (1~6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 제거

            switch (choice) {
                case 1:
                	fmu.readExcelFile(inputFilePath); 
                    break;
                case 2:
                	//3.복사시 경로 입력         	
                	System.out.print("복사할 디렉토리 경로 입력 (예: D:/output/): ");
                	outputDirPath = scanner.nextLine();
                	fmu.createDirectory(outputDirPath); //경로에 디렉토리 존재 조회
                	fmu.copyFile(inputFilePath, outputDirPath);
                    break;
                case 3:
                	System.out.print("복사할 디렉토리 경로 입력 (예: D:/output/): ");
                	outputDirPath = scanner.nextLine();
                	fmu.createDirectory(outputDirPath); //경로에 디렉토리 존재 조회
                    break;
                case 5:
                    
                    break;
                case 6:
                    System.out.println("프로그램 종료.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
        }
    }

}
