package Algorithm;
/*
 * ��ȯ�� ���� ���丮�� ���ϱ� 
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
