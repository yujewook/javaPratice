package FileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import ArrayConvert.Converter;
import RateManager.IncomeDataDTO;
import RateManager.RateCulDTO;
import RateManager.RateInfoDTO;
import RateManager.RateManagerUtil;

public class FileManagerUi {
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("엑셀 및 TXT 파일 관리 프로그램 시작");
        
        FileManagerUtil fmu = new FileManagerUtil();
        
        String inputFilePath;
        String outputDirPath;
        String directory;
        ArrayList<String> txtData;
        List<String> sortFields;
        List<Boolean> isAscending;
        List<String[]> excelData;
        
        while (true) {
            System.out.print("검색할 디렉토리 경로 입력 (예: D:/output/): ");
            directory = scanner.nextLine();
            fmu.searchDirectory(directory);



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
                    System.out.print("원본 파일 경로 입력 (예: D:/test.xlsx 또는 D:/test.txt): ");
                    inputFilePath = scanner.nextLine();
                    if (inputFilePath.endsWith(".xlsx")) {
                        fmu.readExcelFile(inputFilePath);
                    } else if (inputFilePath.endsWith(".txt")) {
                        fmu.readTxtFile(inputFilePath);
                    } else {
                        System.out.println("지원되지 않는 파일 형식입니다.");
                    }
                    break;
                
                case 2:
                    System.out.print("원본 파일 경로 입력 (예: D:/test.xlsx 또는 D:/test.txt): ");
                    inputFilePath = scanner.nextLine();
                    System.out.print("복사할 디렉토리 경로 입력 (예: D:/output/): ");
                    outputDirPath = scanner.nextLine();
                    fmu.createDirectory(outputDirPath);
                    fmu.copyFile(inputFilePath, outputDirPath);
                    break;
                
                case 3:
                    System.out.print("원본 파일 경로 입력 (예: D:/test.xlsx 또는 D:/test.txt): ");
                    inputFilePath = scanner.nextLine();
                    System.out.print("복사할 파일명과 경로 입력 (예: D:/output/sorted.xlsx 또는 D:/output/sorted.txt): ");
                    outputDirPath = scanner.nextLine();
                    if (inputFilePath.endsWith(".xlsx")) {
                        // sortFields: 정렬할 필드순서 ("name" : 0 , "incomeDate" : 1) => "0","1"
                        // isAscending: 오름차순/내림차순 설정 (true: 오름차순, false: 내림차순)
                    	sortFields = Arrays.asList("0", "1");
                        isAscending = Arrays.asList(true, true); // 이름과 날짜는 오름차순 정렬
                        excelData =  fmu.sortExelFile(inputFilePath , sortFields, isAscending ); //파일자체 조회
                        fmu.copySortExcelFile(excelData, outputDirPath );
                    } else if (inputFilePath.endsWith(".txt")) {
                        txtData = fmu.sortTxtFile(inputFilePath);
                        fmu.copySortTxtFile(txtData, outputDirPath);
                    } else {
                        System.out.println("지원되지 않는 파일 형식입니다.");
                    }
                    break;
                
                case 4:
                	//이자
                	List<String[]> excelData1 = null; //입금정보
                	List<String[]> excelData2 = null; //이자정보
                	ArrayList<String> txtData1 = null; //입금정보
                	ArrayList<String> txtData2 = null; //이자정보
                	
                	System.out.println("입금데이터엑셀 파일명과 경로 입력 (예: D:/test.xlsx)");
                	String incomefileName = scanner.nextLine();
                    System.out.print("복사할 파일명과 경로 입력 (예: D:/output/sorted.xlsx 또는 D:/output/sorted.txt): ");
                    outputDirPath = scanner.nextLine();
                   
                    if (incomefileName.endsWith(".xlsx")) {
                        // sortFields: 정렬할 필드순서 ("name" : 0 , "incomeDate" : 1) => "0","1"
                        // isAscending: 오름차순/내림차순 설정 (true: 오름차순, false: 내림차순)
                    	sortFields = Arrays.asList("0", "1");
                        isAscending = Arrays.asList(true, true); // 이름과 날짜는 오름차순 정렬
                        excelData1 =  fmu.sortExelFile(incomefileName , sortFields, isAscending ); //파일자체 조회
                        fmu.copySortExcelFile(excelData1, outputDirPath );
                    } else if (incomefileName.endsWith(".txt")) {
                        txtData1 = fmu.sortTxtFile(incomefileName);
                        fmu.copySortTxtFile(txtData1, outputDirPath );
                    } else {
                        System.out.println("지원되지 않는 파일 형식입니다.");
                    }
                	
                    System.out.println("이자데이터엑셀 파일명과 경로 입력 (예: D:/test1.xlsx)");
                	String ratefileName = scanner.nextLine();
                    if (ratefileName.endsWith(".xlsx")) {
                        // sortFields: 정렬할 필드순서 ("name" : 0 , "incomeDate" : 1) => "0","1"
                        // isAscending: 오름차순/내림차순 설정 (true: 오름차순, false: 내림차순)
                    	sortFields = Arrays.asList("0", "1");
                        isAscending = Arrays.asList(true, true); // 이름과 날짜는 오름차순 정렬
                        excelData2 =  fmu.sortExelFile(ratefileName , sortFields, isAscending ); //파일자체 조회
                        fmu.copySortExcelFile(excelData2, outputDirPath );
                    } else if (ratefileName.endsWith(".txt")) {
                        txtData2 = fmu.sortTxtFile(ratefileName);
                        fmu.copySortTxtFile(txtData2, outputDirPath );
                    } else {
                        System.out.println("지원되지 않는 파일 형식입니다.");
                    }
                    
                    if(null == excelData2 && null == excelData1  ) {
                    	System.out.println("해당데이터가 없습니다.");
                    	break;
                    }

                    Converter convert = new Converter();

                    // FileDataDTO 변환 로직 정의
                    Function<String[], IncomeDataDTO> incomeMapper = row -> {
                        if (row.length < 3) return null; // 데이터 부족 시 무시

                        IncomeDataDTO dto = new IncomeDataDTO();
                        dto.setName(row[0].trim());
                        dto.setIncomeDate(row[1].trim());
                        dto.setIncome(row[2].trim());
                        return dto;
                    };

                    // FileDataRateInfoDTO 변환 로직 정의
                    Function<String[], RateInfoDTO> rateMapper = row -> {
                        if (row.length < 2) return null; // 데이터 부족 시 무시

                        RateInfoDTO dto = new RateInfoDTO();
                        dto.setRateDate(row[0].trim());
                        dto.setRate(row[1].trim());
                        return dto;
                    };

                    // 변환 실행
                    List<IncomeDataDTO> incomeInfo = convert.convertToDTOList(excelData1, incomeMapper);
                    List<RateInfoDTO> rateInfo = convert.convertToDTOList(excelData2, rateMapper);
                    
                    RateManagerUtil rmu = new RateManagerUtil();
                    List<RateCulDTO> rateCulList = rmu.calculateInterest(incomeInfo, rateInfo);
                    // 출력 확인
                    System.out.println("Income Info:");
                    incomeInfo.forEach(System.out::println);

                    System.out.println("\nRate Info:");
                    rateInfo.forEach(System.out::println);

                    System.out.println("\nRateCul Info:");
                    rateCulList.forEach(System.out::println);
                	
                    System.out.println("결과물 파일 타입을 결정해 주세요 (예: TXT | EXCEL) ");
                	String fileType = scanner.nextLine();
                	//fmu.rateCalFile(incomefileName , ratefileName ,fileType);
                	fmu.saveToFile(rateCulList, fileType);
                	break;
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
