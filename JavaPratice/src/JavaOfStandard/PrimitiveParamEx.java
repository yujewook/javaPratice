package JavaOfStandard;
class Data{
	int x ;
}

public class PrimitiveParamEx {
	public static void main (String args[]) {
		Data a = new Data();
		a.x = 10;
		System.out.println("main 메소드 "+a.x);
		
		change(a.x);
		System.out.println("main 메소드 "+a.x);
		
	}
	
	static void change(int x) {
		x = 1000;
		System.out.println("change()메소드 x " + x);
	}
	
}
