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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
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
    
    // T: 데이터 타입, sortFields: 정렬할 필드들, isAscending: 각 필드의 정렬 방향
    public List<String[]> sortExelFile(String inputFilePath, List<String> sortFields, List<Boolean> isAscending) {
        List<String[]> outdata = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new Exception("첫 번째 시트가 존재하지 않습니다.");
            }

            // 헤더 행 유지
            Row headerRow = sheet.getRow(0);
            if (headerRow != null) {
                String[] header = new String[headerRow.getLastCellNum()];
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    Cell cell = headerRow.getCell(i);
                    header[i] = cell != null ? cell.toString() : "";
                }
                outdata.add(header); // 첫 번째 행을 헤더로 추가
            }

            // 데이터 행 읽기
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String[] rowData = new String[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = (cell != null) ? getCellValueAsString(cell) : "";
                }
                outdata.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 정렬 수행 (첫 번째 행(헤더)은 제외하고 정렬)
        if (outdata.size() > 1) {
            Collections.sort(outdata.subList(1, outdata.size()), (o1, o2) -> {
                for (int i = 0; i < sortFields.size(); i++) {
                    int columnIndex = Integer.parseInt(sortFields.get(i)); // 정렬할 컬럼 인덱스
                    boolean ascending = isAscending.get(i);

                    String val1 = o1[columnIndex];
                    String val2 = o2[columnIndex];

                    int comparisonResult = compareValues(val1, val2);

                    if (!ascending) {
                        comparisonResult = -comparisonResult;
                    }

                    if (comparisonResult != 0) {
                        return comparisonResult; // 다른 값이 나오면 정렬 결과 반환
                    }
                }
                return 0; // 모든 조건에서 값이 같으면 유지
            });
        }

        return outdata;
    }

    // 문자열 비교 (숫자는 숫자로 비교)
    private int compareValues(String val1, String val2) {
        try {
            double num1 = Double.parseDouble(val1);
            double num2 = Double.parseDouble(val2);
            return Double.compare(num1, num2);
        } catch (NumberFormatException e) {
            return val1.compareTo(val2); // 숫자가 아니면 문자열 비교
        }
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
            	    double numericValue = cell.getNumericCellValue();

            	    // 숫자가 비어있으면 빈 문자열 반환
            	    if (Double.isNaN(numericValue)) {
            	        return "";
            	    }

            	    // 소수점 이하 값이 존재하는지 확인
            	    if (numericValue % 1 == 0) {
            	        return String.format("%.0f", numericValue); // 정수로 변환
            	    } else {
            	        return String.format("%.2f", numericValue); // 소수점 둘째 자리까지 표시
            	    }
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
    public void copySortExcelFile(List<String[]> outdata, String fileName) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(fileName)) {

            Sheet sheet = workbook.createSheet("Sheet1");

            int rowNum = 0;
            for (String[] rowData : outdata) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < rowData.length; i++) {
                    row.createCell(i).setCellValue(rowData[i]);
                }
            }

            workbook.write(fileOut);
            System.out.println("엑셀 파일이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    /* 1. 입금데이터 데이터 리스트로 뽑아 오기 후 정렬 이름별/날자별 
     * 2. 이자데이터 데이터 리스트로 뽑아 오기 후 정렬 일자별
     * 3. 이자계산메소드 호출
     * 4. txt파일로 만들기
     * 5. 파일만들기 메소드 호출
     */
    /*
    public void rateCalFile (String incomefileName, String ratefileName, String fileType) {
    	
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
        //파일 만들기
        try {
			saveToFile(rateCulList,fileType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
*/


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
    
    //파일 만들기
    public void saveToFile(List<RateCulDTO> data, String fileType) throws IOException {
        // 오늘 날짜 구하기 (yyyyMMdd 형식)
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //이름 지우기
        Set<String> seenNames = new HashSet<>();
        List<RateCulDTO> result = new ArrayList<>();

        for (RateCulDTO dto : data) {
            if (seenNames.contains(dto.getName())) {
                // 중복된 이름은 ""로 변경
                RateCulDTO newDto = new RateCulDTO();
                newDto.setName(""); // 이름을 빈 문자열로 변경
                newDto.setIncomeDate(dto.getIncomeDate());
                newDto.setRateDate(dto.getRateDate());
                newDto.setIncome(dto.getIncome());
                newDto.setDate(dto.getDate());
                newDto.setRate(dto.getRate());
                newDto.setSumRateIncome(dto.getSumRateIncome());
                result.add(newDto);
            } else {
                seenNames.add(dto.getName());
                result.add(dto);
            }
        }
        data = result;
        //이름 지우기
        
        //파일 생성 시작
        if ("TXT".equalsIgnoreCase(fileType)) {
            saveAsTxt(data, today);
        } else if ("EXCEL".equalsIgnoreCase(fileType)) {
            saveAsExcel(data, today);
        } else {
            throw new IllegalArgumentException("지원하지 않는 파일 타입입니다. TXT 또는 EXCEL을 입력하세요.");
        }
    }

    public void saveAsTxt(List<RateCulDTO> data, String today) throws IOException {
        // 컬럼 너비 설정 (각 필드의 최대 길이를 고려한 너비)
        int nameWidth = 15;        // 이름 (한글 5자 정도)
        int incomeWidth = 14;      // 금액 (천단위 구분 포함) -> incomeWidth를 늘림
        int fromDateWidth = 12;    // 시작일 (YYYY-MM-DD)
        int toDateWidth = 12;      // 종료일 (YYYY-MM-DD)
        int daysWidth = 8;         // 일수 (오른쪽 정렬) -> 두 칸 앞으로 당김
        int rateWidth = 10;        // 금리 (오른쪽 정렬) -> 두 칸 앞으로 당김
        int sumRateIncomeWidth = 14; // 금액+이자 -> 두 칸 앞으로 당김

        // 파일 경로 설정
        String fileName = "D:/RateCul_" + today + ".txt";
        DecimalFormat df = new DecimalFormat("#,###");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {

            // 기준일자 작성
            writer.write("기준일자: " + today);
            writer.newLine();
            
            // 헤더 작성 (오른쪽 정렬된 필드 고려)
            String headerFormat = "%-" + nameWidth + "s" +    // 이름 (왼쪽)
                                  "%" + incomeWidth + "s" +     // 금액 (오른쪽)
                                  "%" + fromDateWidth + "s" +   // From Date (오른쪽)
                                  "%" + toDateWidth + "s" +     // To Date (오른쪽)
                                  "%" + daysWidth + "s" +       // 일수 (오른쪽)
                                  "%" + rateWidth + "s" +       // 금리 (오른쪽)
                                  "%" + sumRateIncomeWidth + "s"; // 금액+이자 (오른쪽)
            writer.write(String.format(headerFormat,
                    "이름", "금액", "From Date", "To Date", "일수", "금리", "금액+이자"));
            writer.newLine();

            // 데이터 작성
            for (RateCulDTO dto : data) {
                // 이름 처리 (null일 경우 스페이스 2칸을 추가하여 incomeWidth 만큼 여유 추가)
                String name = (dto.getName() == null || dto.getName().isEmpty()) ? "  " : dto.getName();
                
                // 금액 포맷
                String income = df.format(Long.parseLong(dto.getIncome()));
                String sumRateIncome = df.format(Long.parseLong(dto.getSumRateIncome()));
                
                // 날짜 포맷팅 (YYYYMMDD -> YYYY-MM-DD)
                String fromDateRaw = dto.getIncomeDate();
                String toDateRaw = dto.getRateDate();
                String fromDate = fromDateRaw.length() == 8 ? 
                    fromDateRaw.substring(0,4) + "-" + fromDateRaw.substring(4,6) + "-" + fromDateRaw.substring(6) : 
                    fromDateRaw;
                String toDate = toDateRaw.length() == 8 ? 
                    toDateRaw.substring(0,4) + "-" + toDateRaw.substring(4,6) + "-" + toDateRaw.substring(6) : 
                    toDateRaw;
                
                // 일수와 금리 (오른쪽 정렬)
                String days = dto.getDate();
                String rate = dto.getRate();

                // 데이터 라인 포맷 (헤더와 정렬 일치)
                String lineFormat = "%-" + nameWidth + "s" +    // 이름 (왼쪽)
                                    "%" + incomeWidth + "s" +     // 금액 (오른쪽)
                                    "%" + fromDateWidth + "s" +   // From Date (오른쪽)
                                    "%" + toDateWidth + "s" +     // To Date (오른쪽)
                                    "%" + daysWidth + "s" +       // 일수 (오른쪽)
                                    "%" + rateWidth + "s" +       // 금리 (오른쪽)
                                    "%" + sumRateIncomeWidth + "s"; // 금액+이자 (오른쪽)
                
                writer.write(String.format(lineFormat,
                        name, income, fromDate, toDate, days, rate, sumRateIncome));
                writer.newLine();
            }

            System.out.println("TXT 파일 저장 완료: " + fileName);

        } catch (Exception e) {
            System.err.println("파일 생성 중 오류 발생: " + e.getMessage());
        }
    }
    
    private void saveAsExcel(List<RateCulDTO> data, String today) throws IOException {
    	String fileName = "D:/RateCul_" + today + ".xlsx";
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rate Data");

        // 스타일 설정
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        
        // 헤더 작성
        Row headerRow = sheet.createRow(0);
        String[] headers = {"이름", "금액", "From Date", "To Date", "일수", "금리", "금액+이자"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // 데이터 작성
        int rowNum = 1;
        for (RateCulDTO dto : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dto.getName());
            row.createCell(1).setCellValue(dto.getIncome());
            row.createCell(2).setCellValue(dto.getIncomeDate());
            row.createCell(3).setCellValue(dto.getRateDate());
            row.createCell(4).setCellValue(dto.getDate());
            row.createCell(5).setCellValue(dto.getRate());
            row.createCell(6).setCellValue(dto.getSumRateIncome());
        }

        // 자동 열 너비 조정
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // 파일 저장
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        workbook.close();
        System.out.println("Excel 파일 저장 완료: " + fileName);
    }
    
    
    
}
