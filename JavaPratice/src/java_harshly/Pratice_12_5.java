package java_harshly;

public class Pratice_12_5 {
/*�ﰢ�� �⺻
 * 1. for ������ �ﰢ�� �׸���
    *
   ***
  *****
 *******
********* 
*for(int i = 5 ; i > 0 ; i--) {
			//����
			for(int j = 0 ; 5 >= j ; j++ ) {
				if (j > i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			//����
			for(int j = 5 ; j >= 0  ; j--) {
				if(j>=i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
* 
 */
//	1. ���� for�� �ֻ��
//	2. ���� ������ ���� �������� ���� for������ �ﰢ�� �����
	public static void main(String[] args) {
		//��
		for (int i = 5 ; i > 0 ; i--) {
			for(int j = i ; j < 10 ; j++) {
				if( 10 - i >= j)
					System.out.print("*");
				else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

}
