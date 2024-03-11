package JavaOfStandard;

class Parent{
	int x = 10;
}

class Child extends Parent{
	int x = 20;
	void main() {
		System.out.println("x = " + x);
		System.out.println("this.x = " + this.x);
		System.out.println("super.x = " + super.x);
	}
}

public class SuperTest {
	
	public static void main(String[] args) {
		Child a = new Child();
		a.main();
		System.out.println("a °á°ú ³¡");
		
	}
	

}
