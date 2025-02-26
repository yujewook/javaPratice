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
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
    public <T> List<T> sortExelFile(String inputFilePath, Class<T> clazz, List<String> sortFields, List<Boolean> isAscending) {
        List<T> outdata = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // ù ��° ��Ʈ�� ������
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new Exception("ù ��° ��Ʈ�� �������� �ʽ��ϴ�.");
            }

            // ��Ʈ�� �����͸� ���
            int rowCount = sheet.getPhysicalNumberOfRows(); // ��ü �� ���� ��������

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue; // �� �� ����

                T sortdata = clazz.getDeclaredConstructor().newInstance(); // �������� ��ü ����
                int cellCount = row.getPhysicalNumberOfCells(); // ���� ���� �� ���� ��������

                for (int j = 0; j < cellCount; j++) {
                    Cell cell = row.getCell(j);
                    // �ʵ� �̸��� ���� �� ���� �����ϴ� ���� �ۼ�
                    if (clazz.equals(FileDataDTO.class)) {
                        FileDataDTO data = (FileDataDTO) sortdata;
                        if (j == 0) data.setName(cell.toString());
                        if (j == 1) data.setIncomeDate(getCellValueAsString(cell));
                        if (j == 2) data.setIncome(getCellValueAsString(cell));
                    }
                }
                outdata.add(sortdata); // ����Ʈ�� �߰�
            }
        } catch (FileNotFoundException e) {
            System.out.println("������ ã�� �� �����ϴ�: " + inputFilePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("���� ������ �д� �� ������ �߻��߽��ϴ�.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ���� ����
        Collections.sort(outdata, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                // FileDataDTO Ÿ���� ��� ó��
                if (o1 instanceof FileDataDTO && o2 instanceof FileDataDTO) {
                    FileDataDTO data1 = (FileDataDTO) o1;
                    FileDataDTO data2 = (FileDataDTO) o2;

                    // �ʵ� ������� ���� (sortFields�� �ʵ� �̸�, isAscending�� ����/��������)
                    for (int i = 0; i < sortFields.size(); i++) {
                        String field = sortFields.get(i);
                        boolean ascending = isAscending.get(i);

                        int comparisonResult = 0;
                        if ("name".equalsIgnoreCase(field)) {
                            comparisonResult = data1.getName().compareTo(data2.getName());
                        } else if ("incomeDate".equalsIgnoreCase(field)) {
                            comparisonResult = data1.getIncomeDate().compareTo(data2.getIncomeDate());
                        } else if ("income".equalsIgnoreCase(field)) {
                            comparisonResult = Double.compare(Double.parseDouble(data1.getIncome()), Double.parseDouble(data2.getIncome()));
                        }

                        // ��������/�������� ó��
                        if (!ascending) {
                            comparisonResult = -comparisonResult; // ���������� ��� ����
                        }

                        if (comparisonResult != 0) {
                            return comparisonResult;
                        }
                    }
                }
                return 0;
            }
        });

        for (T data : outdata) {
            System.out.println("���ĵ� ������: " + data);
        }

        return outdata;
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
                    return String.format("%.0f", cell.getNumericCellValue()); // �Ҽ��� ���� ���ڿ��� ��ȯ
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
    public void copySortExcelFile(ArrayList<FileDataDTO> outdata, String fileName, String exfileName) {
        // ���� ���� ����
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        Sheet exSheet;
        
        // ù ��° ��: ��� �ۼ����� �� 
        Row headerRow;
        try (FileInputStream exfis = new FileInputStream(exfileName);
                Workbook exworkbook = new XSSFWorkbook(exfis)) {
               
               exSheet = exworkbook.getSheetAt(0);
               if (exSheet == null) {
                   System.out.println("ù ��° ��Ʈ�� �������� �ʽ��ϴ�.");
                   return;
               }
               
               // ���� ������ ��� ����
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
               System.out.println("������ ã�� �� �����ϴ�: " + exfileName);
               e.printStackTrace();
           } catch (IOException e) {
               System.out.println("���� ������ �д� �� ������ �߻��߽��ϴ�.");
               e.printStackTrace();
           }


        // ������ �ۼ�
        int rowNum = 1;  // ù ��° ������ �� (��� �ٷ� �Ʒ�)
        for (FileDataDTO data : outdata) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getIncomeDate());
            row.createCell(2).setCellValue(data.getIncome()); 
        }

        // ���� ����
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

        System.out.println("���� ������ �����Ǿ����ϴ�.");
    }
    
    /* 1. �Աݵ����� ������ ����Ʈ�� �̾� ���� �� ���� �̸���/���ں� 
     * 2. ���ڵ����� ������ ����Ʈ�� �̾� ���� �� ���� ���ں�
     * 3. ���ڰ��޼ҵ� ȣ��
     * 4. txt���Ϸ� �����
     */
    public void rateCalFile (String incomefileName, String ratefileName) {
    	
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
    }



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
}
