package java_harshly;

public class Pratice_12_2 {
/*�ﰢ�� �⺻
  1. for ������ �ﰢ�� �׸���
   	  *
     **
    ***
   ****
  *****  
 */
	public static void main(String[] args) {
		for(int i = 5 ; i > 0 ; i--) {
			for(int j = 0 ; 5 >= j ; j++ ) {
				if (j >= i) {
					System.out.print("*\t");
				} else {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
		
	}

}
