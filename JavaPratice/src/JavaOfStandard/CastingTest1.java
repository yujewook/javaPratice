package JavaOfStandard;

public class CastingTest1 {

	public static void main(String[] args) {
		Car car = null;
		FireEngine fe = new FireEngine();
		FireEngine fe2 = null;
		
		fe.water("fe 여기서");
		fe.drive("fe 여기서");
		car = fe;
		fe2 = (FireEngine) car;
		

	}

}

class Car{
	String color;
	int door;
	
	void drive(String a){
		System.out.println(a);
		System.out.println("drive ");
	}
	
	void stop(){
		System.out.println("stop ");
	}
	
}

class FireEngine extends Car{
	void water(String a ) {
		System.out.println(a);
		System.out.println("물벼락");
	}
}
