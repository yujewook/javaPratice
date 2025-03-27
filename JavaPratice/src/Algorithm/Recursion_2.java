package Algorithm;
/*
 * 순환을 통한 구간합 구하기 
 */
public class Recursion_2 {

	public static int recursion(int a) {
		if (a<=0) {
			return 0;
		} else {
			return a + recursion(a-1);
		}
	}
	
	public static void main(String[] args) {
		int n = 4;
		recursion(n);
	}

}
