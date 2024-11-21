package java_harshly;

public class Pratice_12_4 {
/*삼각형 기본
 * 1. for 문으로 삼각형 그리기
    *
   ***
  *****
 *******
********* 
*for(int i = 5 ; i > 0 ; i--) {
			//좌측
			for(int j = 0 ; 5 >= j ; j++ ) {
				if (j > i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			//우측
			for(int j = 5 ; j >= 0  ; j--) {
				if(j>=i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		public static void main(String[] args) {
        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (j < 5) {
                    // 좌측 부분
                    if (j < i) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                } else {
                    // 우측 부분
                    if (j < 10 - i) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
* 
 */
//	1. 행적 for문 최상단
//	2. 행적 포문안 좌측 우측으로 각각 for문으로 삼각형 만들기
	public static void main(String[] args) {
		//행
		for (int i = 5 ; i > 0 ; i--) {
			//좌측 채우기
			for(int j = 0 ; j < 5 ; j++) {
				if( i > j ) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
			}
			//우측 채우기
			for(int j = 5 ; j >= 0  ; j--) {
				if(j>=i) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}

}
