package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2018 {
/*
 * 1<=N<=10000(큰수일때 연속된 범위를 구할떄 투 포인터를 사용한다)
 * 자연수 N 연속된 자연수의 합으로 N을 구하는 방법을 구해보자뫼
 */
	  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/************************************************************************
		 * 변수설정 
		 ***********************************************************************/
		int N = sc.nextInt();
		int[] arrayInt = new int [N];
		int sum = 0 ;
		int result = 0;
		/************************************************************************
		 * 데이터 넣기
		 ***********************************************************************/
		for(int i = 0 ; i < N  ; i++) {
			arrayInt[i] = i+1;
		}
		
		/************************************************************************
		 * 데이터 찾기
		 ***********************************************************************/
		for( int i = 0 ; i < N ; i++ ) {
			sum = arrayInt[i];
			if (sum == N) {
				result++;
				break;
			}
			for( int j = i+1 ; j < N  ; j++ ) {
				sum = sum + arrayInt[j];
				
				if ( sum > N ) {
					break;
				} else if ( sum == N) {
					result++;
				}
			}
			
		}
		System.out.println(result);
		
	}
}
