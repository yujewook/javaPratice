package java_harshly;

public class Pratice_12_3 {
/*�ﰢ�� �⺻
 * 1. for ������ �ﰢ�� �׸���
   *
   **
   ***
   ****
   *****  
 */
	public static void main(String[] args) {
		for(int i = 0 ; i < 5 ; i++) {
			for(int j = i+1 ; j > 0 ; j--) {
				System.out.print("*\t");
			}
			System.out.print("\n");
		}
	}

}
