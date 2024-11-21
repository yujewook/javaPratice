package java_harshly;

public class Pratice_12_3 {
/*삼각형 기본
 * 1. for 문으로 삼각형 그리기
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
