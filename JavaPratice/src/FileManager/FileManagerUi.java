package FileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import ArrayConvert.Converter;
import RateManager.IncomeDataDTO;
import RateManager.RateCulDTO;
import RateManager.RateInfoDTO;
import RateManager.RateManagerUtil;

public class FileManagerUi {
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("���� �� TXT ���� ���� ���α׷� ����");
        
        FileManagerUtil fmu = new FileManagerUtil();
        
        String inputFilePath;
        String outputDirPath;
        String directory;
        ArrayList<String> txtData;
        List<String> sortFields;
        List<Boolean> isAscending;
        List<String[]> excelData;
        
        while (true) {
            System.out.print("�˻��� ���丮 ��� �Է� (��: D:/output/): ");
            directory = scanner.nextLine();
            fmu.searchDirectory(directory);



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
                    System.out.print("���� ���� ��� �Է� (��: D:/test.xlsx �Ǵ� D:/test.txt): ");
                    inputFilePath = scanner.nextLine();
                    if (inputFilePath.endsWith(".xlsx")) {
                        fmu.readExcelFile(inputFilePath);
                    } else if (inputFilePath.endsWith(".txt")) {
                        fmu.readTxtFile(inputFilePath);
                    } else {
                        System.out.println("�������� �ʴ� ���� �����Դϴ�.");
                    }
                    break;
                
                case 2:
                    System.out.print("���� ���� ��� �Է� (��: D:/test.xlsx �Ǵ� D:/test.txt): ");
                    inputFilePath = scanner.nextLine();
                    System.out.print("������ ���丮 ��� �Է� (��: D:/output/): ");
                    outputDirPath = scanner.nextLine();
                    fmu.createDirectory(outputDirPath);
                    fmu.copyFile(inputFilePath, outputDirPath);
                    break;
                
                case 3:
                    System.out.print("���� ���� ��� �Է� (��: D:/test.xlsx �Ǵ� D:/test.txt): ");
                    inputFilePath = scanner.nextLine();
                    System.out.print("������ ���ϸ�� ��� �Է� (��: D:/output/sorted.xlsx �Ǵ� D:/output/sorted.txt): ");
                    outputDirPath = scanner.nextLine();
                    if (inputFilePath.endsWith(".xlsx")) {
                        // sortFields: ������ �ʵ���� ("name" : 0 , "incomeDate" : 1) => "0","1"
                        // isAscending: ��������/�������� ���� (true: ��������, false: ��������)
                    	sortFields = Arrays.asList("0", "1");
                        isAscending = Arrays.asList(true, true); // �̸��� ��¥�� �������� ����
                        excelData =  fmu.sortExelFile(inputFilePath , sortFields, isAscending ); //������ü ��ȸ
                        fmu.copySortExcelFile(excelData, outputDirPath );
                    } else if (inputFilePath.endsWith(".txt")) {
                        txtData = fmu.sortTxtFile(inputFilePath);
                        fmu.copySortTxtFile(txtData, outputDirPath);
                    } else {
                        System.out.println("�������� �ʴ� ���� �����Դϴ�.");
                    }
                    break;
                
                case 4:
                	//����
                	List<String[]> excelData1 = null; //�Ա�����
                	List<String[]> excelData2 = null; //��������
                	ArrayList<String> txtData1 = null; //�Ա�����
                	ArrayList<String> txtData2 = null; //��������
                	
                	System.out.println("�Աݵ����Ϳ��� ���ϸ�� ��� �Է� (��: D:/test.xlsx)");
                	String incomefileName = scanner.nextLine();
                    System.out.print("������ ���ϸ�� ��� �Է� (��: D:/output/sorted.xlsx �Ǵ� D:/output/sorted.txt): ");
                    outputDirPath = scanner.nextLine();
                   
                    if (incomefileName.endsWith(".xlsx")) {
                        // sortFields: ������ �ʵ���� ("name" : 0 , "incomeDate" : 1) => "0","1"
                        // isAscending: ��������/�������� ���� (true: ��������, false: ��������)
                    	sortFields = Arrays.asList("0", "1");
                        isAscending = Arrays.asList(true, true); // �̸��� ��¥�� �������� ����
                        excelData1 =  fmu.sortExelFile(incomefileName , sortFields, isAscending ); //������ü ��ȸ
                        fmu.copySortExcelFile(excelData1, outputDirPath );
                    } else if (incomefileName.endsWith(".txt")) {
                        txtData1 = fmu.sortTxtFile(incomefileName);
                        fmu.copySortTxtFile(txtData1, outputDirPath );
                    } else {
                        System.out.println("�������� �ʴ� ���� �����Դϴ�.");
                    }
                	
                    System.out.println("���ڵ����Ϳ��� ���ϸ�� ��� �Է� (��: D:/test1.xlsx)");
                	String ratefileName = scanner.nextLine();
                    if (ratefileName.endsWith(".xlsx")) {
                        // sortFields: ������ �ʵ���� ("name" : 0 , "incomeDate" : 1) => "0","1"
                        // isAscending: ��������/�������� ���� (true: ��������, false: ��������)
                    	sortFields = Arrays.asList("0", "1");
                        isAscending = Arrays.asList(true, true); // �̸��� ��¥�� �������� ����
                        excelData2 =  fmu.sortExelFile(ratefileName , sortFields, isAscending ); //������ü ��ȸ
                        fmu.copySortExcelFile(excelData2, outputDirPath );
                    } else if (ratefileName.endsWith(".txt")) {
                        txtData2 = fmu.sortTxtFile(ratefileName);
                        fmu.copySortTxtFile(txtData2, outputDirPath );
                    } else {
                        System.out.println("�������� �ʴ� ���� �����Դϴ�.");
                    }
                    
                    if(null == excelData2 && null == excelData1  ) {
                    	System.out.println("�ش絥���Ͱ� �����ϴ�.");
                    	break;
                    }

                    Converter convert = new Converter();

                    // FileDataDTO ��ȯ ���� ����
                    Function<String[], IncomeDataDTO> incomeMapper = row -> {
                        if (row.length < 3) return null; // ������ ���� �� ����

                        IncomeDataDTO dto = new IncomeDataDTO();
                        dto.setName(row[0].trim());
                        dto.setIncomeDate(row[1].trim());
                        dto.setIncome(row[2].trim());
                        return dto;
                    };

                    // FileDataRateInfoDTO ��ȯ ���� ����
                    Function<String[], RateInfoDTO> rateMapper = row -> {
                        if (row.length < 2) return null; // ������ ���� �� ����

                        RateInfoDTO dto = new RateInfoDTO();
                        dto.setRateDate(row[0].trim());
                        dto.setRate(row[1].trim());
                        return dto;
                    };

                    // ��ȯ ����
                    List<IncomeDataDTO> incomeInfo = convert.convertToDTOList(excelData1, incomeMapper);
                    List<RateInfoDTO> rateInfo = convert.convertToDTOList(excelData2, rateMapper);
                    
                    RateManagerUtil rmu = new RateManagerUtil();
                    List<RateCulDTO> rateCulList = rmu.calculateInterest(incomeInfo, rateInfo);
                    // ��� Ȯ��
                    System.out.println("Income Info:");
                    incomeInfo.forEach(System.out::println);

                    System.out.println("\nRate Info:");
                    rateInfo.forEach(System.out::println);

                    System.out.println("\nRateCul Info:");
                    rateCulList.forEach(System.out::println);
                	
                    System.out.println("����� ���� Ÿ���� ������ �ּ��� (��: TXT | EXCEL) ");
                	String fileType = scanner.nextLine();
                	//fmu.rateCalFile(incomefileName , ratefileName ,fileType);
                	fmu.saveToFile(rateCulList, fileType);
                	break;
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
