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
해당 클래스에서 메서드를 정적으로 만들면서 인터페이스(인스턴스)를 사용 하고 싶을때 둘다 정적으로 만들어 버리자    
 public class InterfacePraticeClass {
    private static PraticeInterface praticeInterface;  // 정적 변수로 변경

    static void outputMethod () {
        praticeInterface = new praticeInterfaceImpl(); // 정상 동작
    }

    public static void main(String[] args) {
        outputMethod();  // 정적 메서드 호출
    }
} 
 */