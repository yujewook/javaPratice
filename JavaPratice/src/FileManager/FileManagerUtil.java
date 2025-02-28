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
        System.out.println(input + "�� ���丮 .xlsx , .txt ���� ����Ʈ ");
        if (dir.listFiles() == null) {
            throw new Exception("���丮�� .xlsx , .txt ������ �����ϴ�.");
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
                System.out.println("���丮 ������: " + directoryPath);
            } catch (IOException e) {
                System.out.println("���丮 ���� ����: " + directoryPath);
                e.printStackTrace();
            }
        } else {
            System.out.println("���丮 ����: " + directoryPath);
        }
    }

    public void readTxtFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            System.out.println("TXT ���� ����:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("TXT ������ �д� �� ������ �߻��߽��ϴ�.");
            e.printStackTrace();
        }
    }
    
    // ���� ���� �б�
    public void readExcelFile(String filePath) {
        System.out.println("�Էµ� ���� ���: " + filePath); // ��� Ȯ���� ���� ���
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
             
             // ù ��° ��Ʈ�� ������
             Sheet sheet = workbook.getSheetAt(0);
             if (sheet == null) {
                 System.out.println("ù ��° ��Ʈ�� �������� �ʽ��ϴ�.");
                 return;
             }
             
             // ��Ʈ�� �����͸� ���
             for (Row row : sheet) {
                 for (Cell cell : row) {
                     System.out.print(getCellValueAsString(cell) + "|");
                 }
                 System.out.println();
             }
        } catch (FileNotFoundException e) {
            System.out.println("������ ã�� �� �����ϴ�: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("���� ������ �д� �� ������ �߻��߽��ϴ�.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> sortTxtFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // ù ��° ��(���) ����
            if (header != null) {
                lines.add(header); // ����� �������� �ʰ� ����
            }
            
            ArrayList<String> dataLines = new ArrayList<>(); // ���� ��� ����Ʈ
            String line;
            while ((line = br.readLine()) != null) {
                dataLines.add(line);
            }
            
            Collections.sort(dataLines); // ��� ������ ������ ������ ����
            lines.addAll(dataLines); // ��� + ���ĵ� ������ ��ġ��
            
        } catch (IOException e) {
            System.out.println("TXT ������ �д� �� ������ �߻��߽��ϴ�.");
            e.printStackTrace();
        }
        
        return lines;
    }

    public void copySortTxtFile(ArrayList<String> sortedData, String fileName) {
        if (sortedData.isEmpty()) {
            System.out.println("������ �����Ͱ� �����ϴ�.");
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0 ; i < sortedData.size(); i++) {
                bw.write(sortedData.get(i));
                bw.newLine();
            }
            System.out.println("TXT ������ ���ĵǾ� ����Ǿ����ϴ� (��� ����): " + fileName);
        } catch (IOException e) {
            System.out.println("TXT ���� ���� �� ������ �߻��߽��ϴ�.");
            e.printStackTrace();
        }
    }

    public void copyFile(String inputFilePath, String outputDirPath) {
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputDirPath + "/" + inputFile.getName());

            if (inputFile.exists()) {
                Files.copy(inputFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("���� ���� �Ϸ�: " + outputFile.getPath());
            } else {
                System.out.println("���� ������ �������� �ʽ��ϴ�.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // T: ������ Ÿ��, sortFields: ������ �ʵ��, isAscending: �� �ʵ��� ���� ����
    public List<String[]> sortExelFile(String inputFilePath, List<String> sortFields, List<Boolean> isAscending) {
        List<String[]> outdata = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new Exception("ù ��° ��Ʈ�� �������� �ʽ��ϴ�.");
            }

            // ��� �� ����
            Row headerRow = sheet.getRow(0);
            if (headerRow != null) {
                String[] header = new String[headerRow.getLastCellNum()];
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    Cell cell = headerRow.getCell(i);
                    header[i] = cell != null ? cell.toString() : "";
                }
                outdata.add(header); // ù ��° ���� ����� �߰�
            }

            // ������ �� �б�
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

        // ���� ���� (ù ��° ��(���)�� �����ϰ� ����)
        if (outdata.size() > 1) {
            Collections.sort(outdata.subList(1, outdata.size()), (o1, o2) -> {
                for (int i = 0; i < sortFields.size(); i++) {
                    int columnIndex = Integer.parseInt(sortFields.get(i)); // ������ �÷� �ε���
                    boolean ascending = isAscending.get(i);

                    String val1 = o1[columnIndex];
                    String val2 = o2[columnIndex];

                    int comparisonResult = compareValues(val1, val2);

                    if (!ascending) {
                        comparisonResult = -comparisonResult;
                    }

                    if (comparisonResult != 0) {
                        return comparisonResult; // �ٸ� ���� ������ ���� ��� ��ȯ
                    }
                }
                return 0; // ��� ���ǿ��� ���� ������ ����
            });
        }

        return outdata;
    }

    // ���ڿ� �� (���ڴ� ���ڷ� ��)
    private int compareValues(String val1, String val2) {
        try {
            double num1 = Double.parseDouble(val1);
            double num2 = Double.parseDouble(val2);
            return Double.compare(num1, num2);
        } catch (NumberFormatException e) {
            return val1.compareTo(val2); // ���ڰ� �ƴϸ� ���ڿ� ��
        }
    }
    
    //2.0220101E7 ��ȯ ���� �޼ҵ�
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

            	    // ���ڰ� ��������� �� ���ڿ� ��ȯ
            	    if (Double.isNaN(numericValue)) {
            	        return "";
            	    }

            	    // �Ҽ��� ���� ���� �����ϴ��� Ȯ��
            	    if (numericValue % 1 == 0) {
            	        return String.format("%.0f", numericValue); // ������ ��ȯ
            	    } else {
            	        return String.format("%.2f", numericValue); // �Ҽ��� ��° �ڸ����� ǥ��
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
    
    // ���� ���� ���� �޼ҵ�
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
            System.out.println("���� ������ �����Ǿ����ϴ�.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    /* 1. �Աݵ����� ������ ����Ʈ�� �̾� ���� �� ���� �̸���/���ں� 
     * 2. ���ڵ����� ������ ����Ʈ�� �̾� ���� �� ���� ���ں�
     * 3. ���ڰ��޼ҵ� ȣ��
     * 4. txt���Ϸ� �����
     * 5. ���ϸ���� �޼ҵ� ȣ��
     */
    /*
    public void rateCalFile (String incomefileName, String ratefileName, String fileType) {
    	
        RateManagerUtil rmu = new RateManagerUtil();
        List<FileDataDTO> incomeInfo = new ArrayList<>();
        List<FileDataRateInfoDTO> rateList = new ArrayList<>();

        // **�Ա� ������ �б�**
        try (FileInputStream fis = new FileInputStream(incomefileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // ù ��° ��(���) �ǳʶٱ�

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FileDataDTO dto = new FileDataDTO();

                dto.setName(getCellValueAsString(row.getCell(0))); // �̸�
                dto.setIncomeDate(getCellValueAsString(row.getCell(1))); // �Ա�����
                dto.setIncome(Double.toString(getCellValueAsDouble(row.getCell(2)))); // �Աݾ� (���� -> ��Ʈ�� ��ȯ)

                incomeInfo.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // **�ݸ� ������ �б�**
        try (FileInputStream fis = new FileInputStream(ratefileName);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // ù ��° ��(���) �ǳʶٱ�

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                FileDataRateInfoDTO dto = new FileDataRateInfoDTO();

                dto.setRateDate(getCellValueAsString(row.getCell(0))); // �ݸ� ��������
                dto.setRate(Double.toString(getCellValueAsDouble(row.getCell(1)))); // �ݸ� (���� -> ��Ʈ�� ��ȯ)

                rateList.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // **������ ����**
        incomeInfo.sort(Comparator.comparing(FileDataDTO::getName)
                .thenComparing(FileDataDTO::getIncomeDate)); // �̸� �� ��¥ ��

        rateList.sort(Comparator.comparing(FileDataRateInfoDTO::getRateDate)); // ��¥ ��

        // **������ ���**
        System.out.println("=== �Ա� ������ ===");
        for (FileDataDTO data : incomeInfo) {
            System.out.println(data);
        }

        System.out.println("=== �ݸ� ������ ===");
        for (FileDataRateInfoDTO rate : rateList) {
            System.out.println(rate);
        }

        // **���� ���**
        List<RateCulDTO> rateCulList = rmu.calculateInterest(incomeInfo, rateList);

        // **��� ���**
        for (RateCulDTO rateCul : rateCulList) {
            System.out.println(rateCul);
        }
        //���� �����
        try {
			saveToFile(rateCulList,fileType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
*/


    // **���� �� �� �������� (����)**
    private double getCellValueAsDouble(Cell cell) {
        if (cell == null) return 0.0;
        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    return 0.0; // ��ȯ ���� �� �⺻��
                }
            default:
                return 0.0;
        }
    }
    
    //���� �����
    public void saveToFile(List<RateCulDTO> data, String fileType) throws IOException {
        // ���� ��¥ ���ϱ� (yyyyMMdd ����)
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //�̸� �����
        Set<String> seenNames = new HashSet<>();
        List<RateCulDTO> result = new ArrayList<>();

        for (RateCulDTO dto : data) {
            if (seenNames.contains(dto.getName())) {
                // �ߺ��� �̸��� ""�� ����
                RateCulDTO newDto = new RateCulDTO();
                newDto.setName(""); // �̸��� �� ���ڿ��� ����
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
        //�̸� �����
        
        //���� ���� ����
        if ("TXT".equalsIgnoreCase(fileType)) {
            saveAsTxt(data, today);
        } else if ("EXCEL".equalsIgnoreCase(fileType)) {
            saveAsExcel(data, today);
        } else {
            throw new IllegalArgumentException("�������� �ʴ� ���� Ÿ���Դϴ�. TXT �Ǵ� EXCEL�� �Է��ϼ���.");
        }
    }

    public void saveAsTxt(List<RateCulDTO> data, String today) throws IOException {
        // �÷� �ʺ� ���� (�� �ʵ��� �ִ� ���̸� ����� �ʺ�)
        int nameWidth = 15;        // �̸� (�ѱ� 5�� ����)
        int incomeWidth = 14;      // �ݾ� (õ���� ���� ����) -> incomeWidth�� �ø�
        int fromDateWidth = 12;    // ������ (YYYY-MM-DD)
        int toDateWidth = 12;      // ������ (YYYY-MM-DD)
        int daysWidth = 8;         // �ϼ� (������ ����) -> �� ĭ ������ ���
        int rateWidth = 10;        // �ݸ� (������ ����) -> �� ĭ ������ ���
        int sumRateIncomeWidth = 14; // �ݾ�+���� -> �� ĭ ������ ���

        // ���� ��� ����
        String fileName = "D:/RateCul_" + today + ".txt";
        DecimalFormat df = new DecimalFormat("#,###");

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {

            // �������� �ۼ�
            writer.write("��������: " + today);
            writer.newLine();
            
            // ��� �ۼ� (������ ���ĵ� �ʵ� ���)
            String headerFormat = "%-" + nameWidth + "s" +    // �̸� (����)
                                  "%" + incomeWidth + "s" +     // �ݾ� (������)
                                  "%" + fromDateWidth + "s" +   // From Date (������)
                                  "%" + toDateWidth + "s" +     // To Date (������)
                                  "%" + daysWidth + "s" +       // �ϼ� (������)
                                  "%" + rateWidth + "s" +       // �ݸ� (������)
                                  "%" + sumRateIncomeWidth + "s"; // �ݾ�+���� (������)
            writer.write(String.format(headerFormat,
                    "�̸�", "�ݾ�", "From Date", "To Date", "�ϼ�", "�ݸ�", "�ݾ�+����"));
            writer.newLine();

            // ������ �ۼ�
            for (RateCulDTO dto : data) {
                // �̸� ó�� (null�� ��� �����̽� 2ĭ�� �߰��Ͽ� incomeWidth ��ŭ ���� �߰�)
                String name = (dto.getName() == null || dto.getName().isEmpty()) ? "  " : dto.getName();
                
                // �ݾ� ����
                String income = df.format(Long.parseLong(dto.getIncome()));
                String sumRateIncome = df.format(Long.parseLong(dto.getSumRateIncome()));
                
                // ��¥ ������ (YYYYMMDD -> YYYY-MM-DD)
                String fromDateRaw = dto.getIncomeDate();
                String toDateRaw = dto.getRateDate();
                String fromDate = fromDateRaw.length() == 8 ? 
                    fromDateRaw.substring(0,4) + "-" + fromDateRaw.substring(4,6) + "-" + fromDateRaw.substring(6) : 
                    fromDateRaw;
                String toDate = toDateRaw.length() == 8 ? 
                    toDateRaw.substring(0,4) + "-" + toDateRaw.substring(4,6) + "-" + toDateRaw.substring(6) : 
                    toDateRaw;
                
                // �ϼ��� �ݸ� (������ ����)
                String days = dto.getDate();
                String rate = dto.getRate();

                // ������ ���� ���� (����� ���� ��ġ)
                String lineFormat = "%-" + nameWidth + "s" +    // �̸� (����)
                                    "%" + incomeWidth + "s" +     // �ݾ� (������)
                                    "%" + fromDateWidth + "s" +   // From Date (������)
                                    "%" + toDateWidth + "s" +     // To Date (������)
                                    "%" + daysWidth + "s" +       // �ϼ� (������)
                                    "%" + rateWidth + "s" +       // �ݸ� (������)
                                    "%" + sumRateIncomeWidth + "s"; // �ݾ�+���� (������)
                
                writer.write(String.format(lineFormat,
                        name, income, fromDate, toDate, days, rate, sumRateIncome));
                writer.newLine();
            }

            System.out.println("TXT ���� ���� �Ϸ�: " + fileName);

        } catch (Exception e) {
            System.err.println("���� ���� �� ���� �߻�: " + e.getMessage());
        }
    }
    
    private void saveAsExcel(List<RateCulDTO> data, String today) throws IOException {
    	String fileName = "D:/RateCul_" + today + ".xlsx";
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rate Data");

        // ��Ÿ�� ����
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        
        // ��� �ۼ�
        Row headerRow = sheet.createRow(0);
        String[] headers = {"�̸�", "�ݾ�", "From Date", "To Date", "�ϼ�", "�ݸ�", "�ݾ�+����"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // ������ �ۼ�
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

        // �ڵ� �� �ʺ� ����
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // ���� ����
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        workbook.close();
        System.out.println("Excel ���� ���� �Ϸ�: " + fileName);
    }
    
    
    
}
