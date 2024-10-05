package Baekjoon;

import java.util.Scanner;

//������ ���ϱ�
public class Baekjoon_11659 {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		/***********************************************************************
		 * ���� 
		 ***********************************************************************/

		int dataNum = sc.nextInt();

		int range = sc.nextInt();
		int arrData [] = new int[dataNum];
		
		/***********************************************************************
		 *�迭�� �ֱ� ���ڳֱ�
		 ***********************************************************************/
		for ( int i = 0 ; i < arrData.length ; i++ ) {
			arrData[i] = sc.nextInt();
		}

		/***********************************************************************
		 *�迭�� �� ���ϱ�
		 ***********************************************************************/
		int arrDataSum [] = new int[dataNum+1];
		for ( int i = 1 ; i < arrDataSum.length ; i++ ) {
			arrDataSum [i] = arrDataSum [i-1] + arrData[i-1];
		}		
		/***********************************************************************
		 *������ �� ���ϱ�
		 ***********************************************************************/
		while(range != 0) {

			int i = sc.nextInt();

			int j = sc.nextInt();
			System.out.println(arrDataSum[j] - arrDataSum[i-1]);
			range--;
		}
		
	}
}
