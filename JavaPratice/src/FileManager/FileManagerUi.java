package FileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class FileManagerUi {
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("���� �� TXT ���� ���� ���α׷� ����");
        FileManagerUtil fmu = new FileManagerUtil();
        String inputFilePath;
        String outputDirPath;
        String directory;
        ArrayList<String> txtData;
        ArrayList<FileDataDTO> excelData;

        while (true) {
            System.out.print("�˻��� ���丮 ��� �Է� (��: D:/output/): ");
            directory = scanner.nextLine();
            fmu.searchDirectory(directory);

            System.out.print("���� ���� ��� �Է� (��: D:/test.xlsx �Ǵ� D:/test.txt): ");
            inputFilePath = scanner.nextLine();

            System.out.println("\n========= �޴� ���� =========");
            System.out.println("1. ����/TXT ���� �б�");
            System.out.println("2. ���� ����");
            System.out.println("3. ���� ���� �� ����");
            System.out.println("4. ���ڰ������");
            System.out.println("5. ����");
            System.out.print("���� (1~5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (inputFilePath.endsWith(".xlsx")) {
                        fmu.readExcelFile(inputFilePath);
                    } else if (inputFilePath.endsWith(".txt")) {
                        fmu.readTxtFile(inputFilePath);
                    } else {
                        System.out.println("�������� �ʴ� ���� �����Դϴ�.");
                    }
                    break;
                case 2:
                    System.out.print("������ ���丮 ��� �Է� (��: D:/output/): ");
                    outputDirPath = scanner.nextLine();
                    fmu.createDirectory(outputDirPath);
                    fmu.copyFile(inputFilePath, outputDirPath);
                    break;
                case 3:
                    System.out.print("������ ���ϸ�� ��� �Է� (��: D:/output/sorted.xlsx �Ǵ� D:/output/sorted.txt): ");
                    outputDirPath = scanner.nextLine();
                    
                    if (inputFilePath.endsWith(".xlsx")) {
                        excelData = fmu.sortFile(inputFilePath);
                        fmu.copySortExcelFile(excelData, outputDirPath , inputFilePath);
                    } else if (inputFilePath.endsWith(".txt")) {
                        txtData = fmu.sortTxtFile(inputFilePath);
                        fmu.copySortTxtFile(txtData, outputDirPath);
                    } else {
                        System.out.println("�������� �ʴ� ���� �����Դϴ�.");
                    }
                    break;
                case 4:
                	System.out.println("�Աݵ����Ϳ��� ���ϸ�� ��� �Է� (��: D:/test.xlsx)");
                	String incomefileName = scanner.nextLine();
                	System.out.println("���ڵ����Ϳ��� ���ϸ�� ��� �Է� (��: D:/test1.xlsx)");
                	String ratefileName = scanner.nextLine();
                	fmu.rateCalFile(incomefileName , ratefileName);
                    return;
                case 5:
                	System.out.println("���α׷� ����.");
                	return;
                default:
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
        }
    }
    
    
}
