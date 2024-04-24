package JavaOfStandard;
class Output {
	//생성자
	Output(){
	}
	//인스턴스 변수
	int a = 0 ;
	int b = 0 ;
	int result = 0;
}

public class Polymorphism {
    
	Output pluse(Output input) {
		input.result = input.a + input.b ;
		System.out.println("메소드안 : " + input.result);
		return input;
	}
	
	void minus(Output input) {
		input.b = 30;
		input.result = input.a - input.b ;
	}
	
	
	public static void main(String[] args) {
		Polymorphism a = new Polymorphism();
		Output b = new Output();
		b.a = 10 ;
		b.b = 20 ;
		
		a.pluse(b);
		System.out.println("pluse 다음 a " + b.a);
		System.out.println("pluse 다음 b " +b.b);
		System.out.println("메소드 pluse 호출이후 : " + b.result);
		
		a.minus(b);
		System.out.println("minus 다음 a " + b.a);
		System.out.println("minus 다음 b " +b.b);
		System.out.println("메소드 minus 호출이후 : " + b.result);
	}

}
