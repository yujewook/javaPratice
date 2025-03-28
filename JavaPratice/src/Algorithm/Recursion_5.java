package Algorithm;
/*
 * 문자열 구하기 
 */
public class Recursion_5 {

	public static int recursion(String a) {
		if (a.equals("")) {
			return 0;
		} else {
			return 1 + recursion(a.substring(1));
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(recursion("안녕하세요"));
		
	}

}
