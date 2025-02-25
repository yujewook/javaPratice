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



}
