package java_harshly;

public class MerhodPratice {

	public static void main(String[] args) {
		int c = 1;
		int d = 1;
		int result = add(c,d);
		System.out.println(result);
		MerhodPratice mp = new MerhodPratice();
		result = mp.add(c, d, result);
		System.out.println(result);
	}
	
	private static int add(int a, int b) {
		return a+b;
	}
	private  int add(int a, int b, int e) {
		return a+b+e;
	}
}
