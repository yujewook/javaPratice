package JavaOfStandard;
class Output {
	//������
	Output(){
	}
	//�ν��Ͻ� ����
	int a = 0 ;
	int b = 0 ;
	int result = 0;
}

public class Polymorphism {
    
	Output pluse(Output input) {
		input.result = input.a + input.b ;
		System.out.println("�޼ҵ�� : " + input.result);
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
		System.out.println("pluse ���� a " + b.a);
		System.out.println("pluse ���� b " +b.b);
		System.out.println("�޼ҵ� pluse ȣ������ : " + b.result);
		
		a.minus(b);
		System.out.println("minus ���� a " + b.a);
		System.out.println("minus ���� b " +b.b);
		System.out.println("�޼ҵ� minus ȣ������ : " + b.result);
	}

}
