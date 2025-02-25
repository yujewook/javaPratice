package FileManager;

import java.util.ArrayList;
import java.util.Scanner;

public class FileManagerUi {
    // ���α׷� ���� (�޴� ����)
    public void run() throws Exception {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("���� ���� ���� ���α׷� ����");
        FileManagerUtil fmu = new FileManagerUtil(); 
        String inputFilePath ;
        String outputDirPath;
        String directory;
        ArrayList<FileDataDTO> outdata;
        while (true) {
        	//1.���丮 ��ȸ
        	System.out.print("�˻��� ���丮 ��� �Է� (��: D:/output/): ");
        	directory = scanner.nextLine();
        	fmu.searchDirectory(directory);
        	//2.�������ϸ� ��� �Է�        	
        	System.out.print("���� ���� ���� ��� �Է� (��: D:/test.xlsx): ");
        	inputFilePath = scanner.nextLine();

        	System.out.println("\n========= �޴� ���� =========");
            System.out.println("1. ���� ���� �б�");
            System.out.println("2. ���� ���� ����");
            System.out.println("3. ���� ���� sort�� ������ ��ȸ");
            System.out.println("4. ���� ���� sorting �� ���� ���Ͽ� ����");
            System.out.println("5. �������Ϳ� ���ڵ����� Ȱ�뵥����"); 
            System.out.println("6. ����");
            System.out.print("���� (1~6): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // ���� ���� ����

            switch (choice) {
                case 1:
                	fmu.readExcelFile(inputFilePath); 
                    break;
                case 2:
                	//����� ��� �Է�         	
                	System.out.print("������ ���丮 ��� �Է� (��: D:/output/): ");
                	outputDirPath = scanner.nextLine();
                	fmu.createDirectory(outputDirPath); //��ο� ���丮 ���� ��ȸ
                	fmu.copyFile(inputFilePath, outputDirPath);
                    break;
                case 3:
                	fmu.sortFile(inputFilePath);
                    break;
                case 4:       	
                	System.out.print("������ ���ϸ�� ��� �Է� (��: D:/output/test.xlsx): ");
                	outputDirPath = scanner.nextLine();
                	outdata = fmu.sortFile(inputFilePath);
                	fmu.copySortExcelFile(outdata, outputDirPath);
                	break;
                case 5:
                    break;
                case 6:
                    System.out.println("���α׷� ����.");
                    return;
                default:
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
        }
    }

}
