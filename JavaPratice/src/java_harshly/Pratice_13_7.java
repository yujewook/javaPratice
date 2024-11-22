package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_7 {
/*
 * 달팽이 모양
 * 1   2   3   4   5
 * 16  17  18  19  6
   15  24  25  20  7
   14  23  22  21  8
   13  12  11  10  9
 */
/* 1. 행을 내려가는 하는 for 문
 * 2. 좌측으로 가는 for문
 */	
	public static void main(String[] args) {

		int arrayInt[][] = new int [5][5];
		int cnt = 1;
		int rowEndIndex = arrayInt.length;
		int rowCnt = 0; 
		int colEndIndex = arrayInt.length;
		int colCnt = 0;
		
		for( int i = 0 ; i < arrayInt.length ; i++ ) { //행
			//좌에서 우
			//행이 밑으로 갈때 옆칸으로이동 하기 때문에 i부터 시작
			for(int j = i ; j < rowEndIndex ;j++) {
				arrayInt[i][j] = cnt++;
			}
			colEndIndex--;
			rowCnt++;
			//위에서 아래
			for(int j = i ; j <= colEndIndex ;j++) {
				arrayInt[j][colEndIndex] = cnt++;
			}
			rowEndIndex--;
			//좌에서 우
			/*
			for(int j = 0 ; j >= colCnt ;j++) {
				arrayInt[j][colEndIndex] = cnt++;
			} 
			colCnt++;
*/
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}