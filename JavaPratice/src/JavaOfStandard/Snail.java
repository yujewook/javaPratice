package JavaOfStandard;

import java.util.Scanner;

public class Snail {

	static Boolean snailCheck(int[][] a) {
		Boolean confirm = true;
		for (int i = 0 ; i < a.length ; i++) {
			for (int j = 0 ; j < a.length ; j++) {
				if (a[i][j] == 0) {
					confirm = false;
				}
			}
		}
		return confirm;
	}
	
	public static void main(String[] args) {
		System.out.println("정육각형 한 변에 위 치할 숫자");
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		int snail [][] = new int[count][count];
		int row = 0; //행
		int col = 0; //열
		int rowEnd = count-1;	//경계선 인덱스
		int colEnd = count-1;   //경계선 인덱스
		int result = 1; // 값
		
		do{
			//좌 -> 우 (행이늘어남)
			for(int i = col; i <= colEnd ; i++  ) {
				snail[row][i] = result ++;
			}
			row++; // 좌 -> 우 다음행 및 위에서 아래 행 하나 밑에서 시작
			
			//위에서 아래 열 한줄이 늘어남
			for( int i = row ; i <= colEnd ; i++ ) {
				snail[i][colEnd] = result++;
			}
			colEnd--; //경계선 마지막
			
			//좌 <- 우 (행이 늘어남)
			if(row <= rowEnd) {
				for(int i = colEnd ; i >= col  ; i-- ) {
					snail[rowEnd][i] = result++;
				}
				rowEnd--;
			}
			
			//아래에서 위 (열이늘어남)
			if(col <= colEnd) {
				for(int i = rowEnd ; i >= row  ; i-- ) {
					snail[i][col] = result++;
				}
				col++;
			}
		
		}while(!snailCheck(snail)); 
		
        // 결과 출력
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail[i].length; j++) {
                System.out.print(snail[i][j] + "\t");
            }
            System.out.println();
        }
	}

}
