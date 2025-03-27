package Algorithm;
/*
 * 순환을 통한 팩토리얼 구하기 
 */
public class Recursion_3 {

	public static int recursion(int a) {
		if (a<=0) {
			return 1;
		} else {
			return a*recursion(a-1);
		}
	}
	
	public static void main(String[] args) {
		int n = 4;
		System.out.println(recursion(n));
		
	}

}
