package MainToRun;

import FileManager.FileManagerUi;

public class FileMain {
	//���� ����
	public static void main(String[] args) {
		FileManagerUi fmu= new FileManagerUi();
		try {
			fmu.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
