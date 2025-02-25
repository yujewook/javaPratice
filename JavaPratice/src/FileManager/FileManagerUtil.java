package FileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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



}
