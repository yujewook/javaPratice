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
    //���丮 ��ü .xlsx .txt ���� �˻�  
    public void searchDirectory(String input) throws Exception {
       String DATA_DIRECTORY = input;
       File dir = new File(DATA_DIRECTORY);
       System.out.println(input+ "�� ���丮 .xlsx , .txt ���ϸ���Ʈ ");
       if ( null == dir.listFiles() ) {
    	   throw new Exception("���丮�� .xlsx , .txt �� �����ϴ�.");
       }
       File files[] = dir.listFiles();
       // 	���� �̸��� .xlsx .txt�� �������� Ȯ��
       for (File file : files) {
           if (file.getName().endsWith(".xlsx")||file.getName().endsWith(".txt")) {  
               System.out.println(file.getPath());
           }
       }
       System.out.println("=====================================================");
    }
    
    // ���丮 ����
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
                     System.out.print(cell.toString() + "|");
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
    
    // ���� ���� ���� (������ ������ ����)
    public void copyFile(String inputFilePath, String outputDirPath) {
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputDirPath + new File(inputFilePath).getName());

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

    //���� ���� sorting �ϱ�
    public ArrayList<FileDataDTO> sortFile(String inputFilePath) {
    	ArrayList<FileDataDTO> outdata = new ArrayList<FileDataDTO>(); 
        FileDataDTO sortdata;
        
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
                    
                    sortdata = new FileDataDTO(); // DTO ��ü ����
                    
                    int cellCount = row.getPhysicalNumberOfCells(); // ���� ���� �� ���� ��������

                    for (int j = 0; j < cellCount; j++) {
                        Cell cell = row.getCell(j);
                        if(j==0)
                        sortdata.setName(cell.toString());	
                        if(j==1)
                        sortdata.setIncomeDate(cell.toString());	
                        if(j==2)
                        sortdata.setIncome(cell.toString());
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
        Collections.sort(outdata , new Comparator<FileDataDTO>() {
            @Override
            public int compare(FileDataDTO o1, FileDataDTO o2) {
                // 1. name(�̸�) ��������
                int nameCompare = o1.getName().compareTo(o2.getName());
                if (nameCompare != 0) return nameCompare;

                // 2. incomeDate(�Ա� ��¥) ��������
                int dateCompare = o1.getIncomeDate().compareTo(o2.getIncomeDate());
                if (dateCompare != 0) return dateCompare;

                // 3. income(�ݾ�) ��������
                return o2.getIncome().compareTo(o1.getIncome());
            }
        });
        
        for (FileDataDTO data : outdata) {
        	System.out.println(data.toString());
        }
    	return outdata;
    }

    // ���� ���� ���� �޼ҵ�
    public void copySortExcelFile(ArrayList<FileDataDTO> outdata, String fileName) {
        // ���� ���� ����
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // ù ��° ��: ��� �ۼ�
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("����");
        headerRow.createCell(1).setCellValue("�Աݳ�¥");
        headerRow.createCell(2).setCellValue("�Աݱݾ�");

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
    
}
