package JavaOfStandard;
class Data2{
	int x ;
}

public class ReferenceParamEx {
	public static void main (String args[]) {
		Data2 a = new Data2();
		a.x = 10;
		System.out.println("main 메소드 "+a.x);
		
		change(a);
		System.out.println("main 메소드 "+a.x);
		
	}
	
	static void change(Data2 x) {
		x.x = 1000;
		System.out.println("change()메소드 x는:: " + x.x);
	}
	
}
