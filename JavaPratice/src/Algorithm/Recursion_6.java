package Algorithm;
/*
 * ������ ���ϱ�
 */
public class Recursion_6 {

	public static void recursion(int input) {
		if (input < 2) {
			System.out.print(input);
		} else {
			recursion(input/2);
			System.out.print(input%2);
		}
	}
	
	public static void main(String[] args) {
		recursion(2);
		
	}

}
