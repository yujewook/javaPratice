package FileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileManagerUtil {
    //디렉토리 전체 .xlsx .txt 파일 검색  
    public void searchDirectory(String input) throws Exception {
       String DATA_DIRECTORY = input;
       File dir = new File(DATA_DIRECTORY);
       System.out.println(input+ "의 디렉토리 .xlsx , .txt 파일리스트 ");
       if ( null == dir.listFiles() ) {
    	   throw new Exception("디렉토리에 .xlsx , .txt 가 없습니다.");
       }
       File files[] = dir.listFiles();
       // 	파일 이름이 .xlsx .txt로 끝나는지 확인
       for (File file : files) {
           if (file.getName().endsWith(".xlsx")||file.getName().endsWith(".txt")) {  
               System.out.println(file.getPath());
           }
       }
       System.out.println("=====================================================");
    }
    
    // 디렉토리 생성
    public void createDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("디렉토리 생성됨: " + directoryPath);
            } catch (IOException e) {
                System.out.println("디렉토리 생성 실패: " + directoryPath);
                e.printStackTrace();
            }
        } else {
            System.out.println("디렉토리 존재: " + directoryPath);
        }
    }
    
    // 엑셀 파일 읽기
    public void readExcelFile(String filePath) {
        System.out.println("입력된 파일 경로: " + filePath); // 경로 확인을 위한 출력
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
             
             // 첫 번째 시트를 가져옴
             Sheet sheet = workbook.getSheetAt(0);
             if (sheet == null) {
                 System.out.println("첫 번째 시트가 존재하지 않습니다.");
                 return;
             }
             
             // 시트의 데이터를 출력
             for (Row row : sheet) {
                 for (Cell cell : row) {
                     System.out.print(cell.toString() + "|");
                 }
                 System.out.println();
             }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("엑셀 파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    
    // 엑셀 파일 복사 (동일한 데이터 유지)
    public void copyFile(String inputFilePath, String outputDirPath) {
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputDirPath + new File(inputFilePath).getName());

            if (inputFile.exists()) {
                Files.copy(inputFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("파일 복사 완료: " + outputFile.getPath());
            } else {
                System.out.println("원본 파일이 존재하지 않습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //엑셀 파일 sorting 하기
    public ArrayList<FileDataDTO> sortFile(String inputFilePath) {
    	ArrayList<FileDataDTO> outdata = new ArrayList<FileDataDTO>(); 
        FileDataDTO sortdata;
        
        try (FileInputStream fis = new FileInputStream(inputFilePath);
                Workbook workbook = new XSSFWorkbook(fis)) {
                
                // 첫 번째 시트를 가져옴
                Sheet sheet = workbook.getSheetAt(0);
                if (sheet == null) {
                    throw new Exception("첫 번째 시트가 존재하지 않습니다.");
                }

                // 시트의 데이터를 출력
                int rowCount = sheet.getPhysicalNumberOfRows(); // 전체 행 개수 가져오기

                for (int i = 1; i < rowCount; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue; // 빈 행 무시
                    
                    sortdata = new FileDataDTO(); // DTO 객체 생성
                    
                    int cellCount = row.getPhysicalNumberOfCells(); // 현재 행의 셀 개수 가져오기

                    for (int j = 0; j < cellCount; j++) {
                        Cell cell = row.getCell(j);
                        if(j==0)
                        sortdata.setName(cell.toString());	
                        if(j==1)
                        sortdata.setIncomeDate(cell.toString());	
                        if(j==2)
                        sortdata.setIncome(cell.toString());
                    }
                    outdata.add(sortdata); // 리스트에 추가
                }
           } catch (FileNotFoundException e) {
        	   System.out.println("파일을 찾을 수 없습니다: " + inputFilePath);
               e.printStackTrace();
           } catch (IOException e) {
               System.out.println("엑셀 파일을 읽는 중 오류가 발생했습니다.");
               e.printStackTrace();
           } catch (Exception e) {
        	   e.printStackTrace();
		}
        
        // 정렬 수행
        Collections.sort(outdata , new Comparator<FileDataDTO>() {
            @Override
            public int compare(FileDataDTO o1, FileDataDTO o2) {
                // 1. name(이름) 오름차순
                int nameCompare = o1.getName().compareTo(o2.getName());
                if (nameCompare != 0) return nameCompare;

                // 2. incomeDate(입금 날짜) 오름차순
                int dateCompare = o1.getIncomeDate().compareTo(o2.getIncomeDate());
                if (dateCompare != 0) return dateCompare;

                // 3. income(금액) 내림차순
                return o2.getIncome().compareTo(o1.getIncome());
            }
        });
        
        for (FileDataDTO data : outdata) {
        	System.out.println(data.toString());
        }
    	return outdata;
    }

    // 엑셀 파일 생성 메소드
    public void copySortExcelFile(ArrayList<FileDataDTO> outdata, String fileName) {
        // 엑셀 파일 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // 첫 번째 행: 헤더 작성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("성명");
        headerRow.createCell(1).setCellValue("입금날짜");
        headerRow.createCell(2).setCellValue("입금금액");

        // 데이터 작성
        int rowNum = 1;  // 첫 번째 데이터 행 (헤더 바로 아래)
        for (FileDataDTO data : outdata) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getIncomeDate());
            row.createCell(2).setCellValue(data.getIncome()); 
        }

        // 파일 저장
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("엑셀 파일이 생성되었습니다.");
    }
    
}
