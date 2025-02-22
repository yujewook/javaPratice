package test;

import java.io.*;
import java.util.Scanner;

public class FileRead1 {
    public static void main(String[] args) {
        FileOperations fileOps = new FileOperations();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n�޴��� �����ϼ���:");
            System.out.println("1. ���� ����");
            System.out.println("2. ���� ����");
            System.out.println("3. ���� �б�");
            System.out.println("4. ���� ����");
            System.out.println("0. ����");
            System.out.print("����: ");

            int choice = sc.nextInt();
            sc.nextLine(); // ���� ���� ����

            try {
                switch (choice) {
                    case 1:
                        System.out.print("������ ������ ��ü ��� �Է� (��: D:/test.txt): ");
                        String createFilePath = sc.nextLine();
                        fileOps.createFile(createFilePath);
                        break;
                    case 2:
                        System.out.print("������ ���� ���� ��� �Է� (��: D:/test.txt): ");
                        String sourcePath = sc.nextLine();
                        System.out.print("����� ������ ��� �Է� (��: D:/test_copy.txt): ");
                        String copyPath = sc.nextLine();
                        fileOps.copyFile(sourcePath, copyPath);
                        break;
                    case 3:
                        System.out.print("���� ������ ��ü ��� �Է� (��: D:/test.txt): ");
                        String readFilePath = sc.nextLine();
                        fileOps.readFile(readFilePath);
                        break;
                    case 4:
                        System.out.print("������ ������ ��ü ��� �Է� (��: D:/test.txt): ");
                        String deleteFilePath = sc.nextLine();
                        fileOps.deleteFile(deleteFilePath);
                        break;
                    case 0:
                        System.out.println("���α׷��� �����մϴ�.");
                        sc.close();
                        return;
                    default:
                        System.out.println("�ùٸ� ��ȣ�� �Է��ϼ���.");
                }
            } catch (IOException e) {
                System.err.println("���� ó�� �� ���� �߻�: " + e.getMessage());
            }
        }
    }
}

class FileOperations {

    // ���� ���� �޼���
    public void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("�̹� �����ϴ� �����Դϴ�.");
            return;
        }
        if (file.createNewFile()) {
            // 3. Buffer�� ����ؼ� File�� write�� �� �ִ� BufferedWriter ���� 
        	FileWriter fw = new FileWriter(file);
        	BufferedWriter writer = new BufferedWriter(fw);
        	// 4. ���Ͽ� ����            
        	Scanner sc = new Scanner(System.in);
        	System.out.println(filePath +" ���� DATA �Է� : ");
        	String inputData = sc.nextLine();
        	writer.write(inputData);             
        	// 5. BufferedWriter close 
        	sc.close();
        	writer.close();
        	
            System.out.println("���� ���� �Ϸ�: " + filePath);
            
        } else {
            System.out.println("���� ���� ����.");
        }
    }

    // ���� ���� �޼��� (�� �پ� �о� ����)
    public void copyFile(String sourcePath, String destinationPath) throws IOException {
        File source = new File(sourcePath);

        if (!source.exists()) {
            System.err.println("���� ������ �������� �ʽ��ϴ�.");
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
        System.out.println("���� ���� �Ϸ�: " + destinationPath);
    }

    // ����� ���Ͽ� �� �پ� �߰��ϴ� �޼���
    private void copyMethod(String fileName, String line) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        }
    }

    // ���� �б� �޼���
    public void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("������ �������� �ʽ��ϴ�.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("\n--- ���� ���� ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--- �� ---");
        }
    }

    // ���� ���� �޼���
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("������ �������� �ʽ��ϴ�.");
            return;
        }
        if (file.delete()) {
            System.out.println("���� ���� �Ϸ�: " + filePath);
        } else {
            System.out.println("���� ���� ����.");
        }
    }
}
