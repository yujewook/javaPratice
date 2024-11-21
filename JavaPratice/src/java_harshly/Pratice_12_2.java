package java_harshly;

public class Pratice_12_2 {
/*삼각형 기본
  1. for 문으로 삼각형 그리기
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
