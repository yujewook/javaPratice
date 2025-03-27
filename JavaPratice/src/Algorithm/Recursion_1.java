package Algorithm;

public class Recursion_1 {

	public static void recursion(int a) {
		if (a<=0) {
			System.out.println("recursion 종료");
			return;
		} else {
			System.out.println("recursion 실행");
			recursion(a-1);
		}
	}
	
	public static void main(String[] args) {
		int n = 4;
		recursion(n);
	}

}
