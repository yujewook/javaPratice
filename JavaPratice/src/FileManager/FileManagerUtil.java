package FileManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import RateManager.RateCulDTO;
import RateManager.RateManagerUtil;

public class FileManagerUtil {

    public void searchDirectory(String input) throws Exception {
        File dir = new File(input);
        System.out.println(input + "의 디렉토리 .xlsx , .txt 파일 리스트 ");
        if (dir.listFiles() == null) {
            throw new Exception("디렉토리에 .xlsx , .txt 파일이 없습니다.");
        }
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith(".xlsx") || file.getName().endsWith(".txt")) {
                System.out.println(file.getPath());
            }
        }
        System.out.println("=====================================================");
    }

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

    public void readTxtFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("TXT 파일 내용:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("TXT 파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
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
                     System.out.print(getCellValueAsString(cell) + "|");
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

    public ArrayList<String> sortTxtFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // 첫 번째 줄(헤더) 저장
            if (header != null) {
                lines.add(header); // 헤더는 정렬하지 않고 유지
            }
            
            ArrayList<String> dataLines = new ArrayList<>(); // 정렬 대상 리스트
            String line;
            while ((line = br.readLine()) != null) {
                dataLines.add(line);
            }
            
            Collections.sort(dataLines); // 헤더 제외한 나머지 데이터 정렬
            lines.addAll(dataLines); // 헤더 + 정렬된 데이터 합치기
            
        } catch (IOException e) {
            System.out.println("TXT 파일을 읽는 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        
        return lines;
    }

    public void copySortTxtFile(ArrayList<String> sortedData, String fileName) {
        if (sortedData.isEmpty()) {
            System.out.println("정렬할 데이터가 없습니다.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0 ; i < sortedData.size(); i++) {
                bw.write(sortedData.get(i));
                bw.newLine();
            }
            System.out.println("TXT 파일이 정렬되어 저장되었습니다 (헤더 제외): " + fileName);
        } catch (IOException e) {
            System.out.println("TXT 파일 저장 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    public void copyFile(String inputFilePath, String outputDirPath) {
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputDirPath + "/" + inputFile.getName());

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
                        sortdata.setIncomeDate(getCellValueAsString(cell));	
                        if(j==2)
                        sortdata.setIncome(getCellValueAsString(cell));
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
        	System.out.println("복사이후"+data.toString());
        }
    	return outdata;
    }
    
    //2.0220101E7 변환 방지 메소드
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.format("%.0f", cell.getNumericCellValue()); // 소수점 없는 문자열로 변환
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    // 엑셀 파일 생성 메소드
    public void copySortExcelFile(ArrayList<FileDataDTO> outdata, String fileName, String exfileName) {
        // 엑셀 파일 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Sheet exSheet;
        
        // 첫 번째 행: 헤더 작성위한 것 
        Row headerRow;
        try (FileInputStream exfis = new FileInputStream(exfileName);
                Workbook exworkbook = new XSSFWorkbook(exfis)) {
               
               exSheet = exworkbook.getSheetAt(0);
               if (exSheet == null) {
                   System.out.println("첫 번째 시트가 존재하지 않습니다.");
                   return;
               }
               
               // 기존 파일의 헤더 복사
               Row exHeaderRow = exSheet.getRow(0);
               headerRow = sheet.createRow(0);
               
               if (exHeaderRow != null) {
                   for (int i = 0; i < exHeaderRow.getLastCellNum(); i++) {
                       Cell exCell = exHeaderRow.getCell(i);
                       if (exCell != null) {
                           headerRow.createCell(i).setCellValue(exCell.getStringCellValue());
                       }
                   }
               }
           } catch (FileNotFoundException e) {
               System.out.println("파일을 찾을 수 없습니다: " + exfileName);
               e.printStackTrace();
           } catch (IOException e) {
               System.out.println("엑셀 파일을 읽는 중 오류가 발생했습니다.");
               e.printStackTrace();
           }


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
    
    /* 1. 입금데이터 데이터 리스트로 뽑아 오기 후 정렬 이름별/날자별 
     * 2. 이자데이터 데이터 리스트로 뽑아 오기 후 정렬 일자별
     * 3. 이자계산메소드 호출
     * 4. txt파일로 만들기
     */
    public void rateCalFile (String incomefileName, String ratefileName) {
    	
        RateManagerUtil rmu = new RateManagerUtil();
        List<FileDataDTO> incomeInfo = new ArrayList<>();
        List<FileDataRateInfoDTO> rateList = new ArrayList<>();

        // **입금 데이터 읽기**
        try (FileInputStream fis = new FileInputStream(incomefileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // 첫 번째 행(헤더) 건너뛰기

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FileDataDTO dto = new FileDataDTO();

                dto.setName(getCellValueAsString(row.getCell(0))); // 이름
                dto.setIncomeDate(getCellValueAsString(row.getCell(1))); // 입금일자
                dto.setIncome(Double.toString(getCellValueAsDouble(row.getCell(2)))); // 입금액 (숫자 -> 스트링 변환)

                incomeInfo.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // **금리 데이터 읽기**
        try (FileInputStream fis = new FileInputStream(ratefileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // 첫 번째 행(헤더) 건너뛰기

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FileDataRateInfoDTO dto = new FileDataRateInfoDTO();

                dto.setRateDate(getCellValueAsString(row.getCell(0))); // 금리 적용일자
                dto.setRate(Double.toString(getCellValueAsDouble(row.getCell(1)))); // 금리 (숫자 -> 스트링 변환)

                rateList.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // **데이터 정렬**
        incomeInfo.sort(Comparator.comparing(FileDataDTO::getName)
                .thenComparing(FileDataDTO::getIncomeDate)); // 이름 → 날짜 순

        rateList.sort(Comparator.comparing(FileDataRateInfoDTO::getRateDate)); // 날짜 순

        // **디버깅용 출력**
        System.out.println("=== 입금 데이터 ===");
        for (FileDataDTO data : incomeInfo) {
            System.out.println(data);
        }

        System.out.println("=== 금리 데이터 ===");
        for (FileDataRateInfoDTO rate : rateList) {
            System.out.println(rate);
        }

        // **이자 계산**
        List<RateCulDTO> rateCulList = rmu.calculateInterest(incomeInfo, rateList);

        // **결과 출력**
        for (RateCulDTO rateCul : rateCulList) {
            System.out.println(rateCul);
        }
    }



    // **엑셀 셀 값 가져오기 (숫자)**
    private double getCellValueAsDouble(Cell cell) {
        if (cell == null) return 0.0;
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    return 0.0; // 변환 실패 시 기본값
                }
            default:
                return 0.0;
        }
    }
}
