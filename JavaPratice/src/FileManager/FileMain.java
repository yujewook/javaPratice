package FileManager;

public class FileMain {
	//���� ����
	public static void main(String[] args) {
		FileManagerUi fmu= new FileManagerUi(new FileDto());
		try {
			fmu.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
