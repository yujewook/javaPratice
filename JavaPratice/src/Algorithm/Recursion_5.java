package Algorithm;
/*
 * ���ڿ� ���ϱ� 
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
		
		System.out.println(recursion("�ȳ��ϼ���"));
		
	}

}
