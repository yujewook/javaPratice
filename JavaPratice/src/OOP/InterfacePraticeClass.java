package OOP;

public class InterfacePraticeClass {
	private PraticeInterface praticeInterface;
	
	void outputMethod () {
		praticeInterface = new praticeInterfaceImpl();
		System.out.println(praticeInterface.outputInterface());
	}
	public static void main(String[] args) {
		InterfacePraticeClass ic = new InterfacePraticeClass(); 
		ic.outputMethod();
	}
}

/*
 * 
�ش� Ŭ�������� �޼��带 �������� ����鼭 �������̽�(�ν��Ͻ�)�� ��� �ϰ� ������ �Ѵ� �������� ����� ������    
 public class InterfacePraticeClass {
    private static PraticeInterface praticeInterface;  // ���� ������ ����

    static void outputMethod () {
        praticeInterface = new praticeInterfaceImpl(); // ���� ����
    }

    public static void main(String[] args) {
        outputMethod();  // ���� �޼��� ȣ��
    }
} 
 */