package JavaOfStandard;
class Data{
	int x ;
}

public class PrimitiveParamEx {
	public static void main (String args[]) {
		Data a = new Data();
		a.x = 10;
		System.out.println("main �޼ҵ� "+a.x);
		
		change(a.x);
		System.out.println("main �޼ҵ� "+a.x);
		
	}
	
	static void change(int x) {
		x = 1000;
		System.out.println("change()�޼ҵ� x " + x);
	}
	
}
