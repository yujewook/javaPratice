package test;

import java.io.*;
import java.util.Scanner;

public class FileRead1 {
    public static void main(String[] args) {
        FileOperations fileOps = new FileOperations();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n메뉴를 선택하세요:");
            System.out.println("1. 파일 생성");
            System.out.println("2. 파일 복사");
            System.out.println("3. 파일 읽기");
            System.out.println("4. 파일 삭제");
            System.out.println("0. 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine(); // 개행 문자 제거

            try {
                switch (choice) {
                    case 1:
                        System.out.print("생성할 파일의 전체 경로 입력 (예: D:/test.txt): ");
                        String createFilePath = sc.nextLine();
                        fileOps.createFile(createFilePath);
                        break;
                    case 2:
                        System.out.print("복사할 원본 파일 경로 입력 (예: D:/test.txt): ");
                        String sourcePath = sc.nextLine();
                        System.out.print("복사된 파일의 경로 입력 (예: D:/test_copy.txt): ");
                        String copyPath = sc.nextLine();
                        fileOps.copyFile(sourcePath, copyPath);
                        break;
                    case 3:
                        System.out.print("읽을 파일의 전체 경로 입력 (예: D:/test.txt): ");
                        String readFilePath = sc.nextLine();
                        fileOps.readFile(readFilePath);
                        break;
                    case 4:
                        System.out.print("삭제할 파일의 전체 경로 입력 (예: D:/test.txt): ");
                        String deleteFilePath = sc.nextLine();
                        fileOps.deleteFile(deleteFilePath);
                        break;
                    case 0:
                        System.out.println("프로그램을 종료합니다.");
                        sc.close();
                        return;
                    default:
                        System.out.println("올바른 번호를 입력하세요.");
                }
            } catch (IOException e) {
                System.err.println("파일 처리 중 오류 발생: " + e.getMessage());
            }
        }
    }
}

class FileOperations {

    // 파일 생성 메서드
    public void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("이미 존재하는 파일입니다.");
            return;
        }
        if (file.createNewFile()) {
            // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성 
        	FileWriter fw = new FileWriter(file);
        	BufferedWriter writer = new BufferedWriter(fw);
        	// 4. 파일에 쓰기            
        	Scanner sc = new Scanner(System.in);
        	System.out.println(filePath +" 파일 DATA 입력 : ");
        	String inputData = sc.nextLine();
        	writer.write(inputData);             
        	// 5. BufferedWriter close 
        	sc.close();
        	writer.close();
        	
            System.out.println("파일 생성 완료: " + filePath);
            
        } else {
            System.out.println("파일 생성 실패.");
        }
    }

    // 파일 복사 메서드 (한 줄씩 읽어 복사)
    public void copyFile(String sourcePath, String destinationPath) throws IOException {
        File source = new File(sourcePath);

        if (!source.exists()) {
            System.err.println("원본 파일이 존재하지 않습니다.");
            return;
        }

        File copyFile = new File(destinationPath);

        if (copyFile.exists()) {
            copyFile.delete();
        }
        copyFile.createNewFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                copyMethod(destinationPath, line);
            }
        }
        System.out.println("파일 복사 완료: " + destinationPath);
    }

    // 복사된 파일에 한 줄씩 추가하는 메서드
    private void copyMethod(String fileName, String line) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        }
    }

    // 파일 읽기 메서드
    public void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("파일이 존재하지 않습니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("\n--- 파일 내용 ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--- 끝 ---");
        }
    }

    // 파일 삭제 메서드
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("파일이 존재하지 않습니다.");
            return;
        }
        if (file.delete()) {
            System.out.println("파일 삭제 완료: " + filePath);
        } else {
            System.out.println("파일 삭제 실패.");
        }
    }
}
