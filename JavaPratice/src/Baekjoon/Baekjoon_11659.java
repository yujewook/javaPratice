package Baekjoon;

import java.util.Scanner;

//구간합 구하기
public class Baekjoon_11659 {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		/***********************************************************************
		 * 변수 
		 ***********************************************************************/

		int dataNum = sc.nextInt();

		int range = sc.nextInt();
		int arrData [] = new int[dataNum];
		
		/***********************************************************************
		 *배열에 넣기 숫자넣기
		 ***********************************************************************/
		for ( int i = 0 ; i < arrData.length ; i++ ) {
			arrData[i] = sc.nextInt();
		}

		/***********************************************************************
		 *배열의 합 구하기
		 ***********************************************************************/
		int arrDataSum [] = new int[dataNum+1];
		for ( int i = 1 ; i < arrDataSum.length ; i++ ) {
			arrDataSum [i] = arrDataSum [i-1] + arrData[i-1];
		}		
		/***********************************************************************
		 *범위의 합 구하기
		 ***********************************************************************/
		while(range != 0) {

			int i = sc.nextInt();

			int j = sc.nextInt();
			System.out.println(arrDataSum[j] - arrDataSum[i-1]);
			range--;
		}
		
	}
}
