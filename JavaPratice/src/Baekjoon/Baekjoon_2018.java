package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2018 {
/*
 * 1<=N<=10000(ū���϶� ���ӵ� ������ ���ҋ� �� �����͸� ����Ѵ�)
 * �ڿ��� N ���ӵ� �ڿ����� ������ N�� ���ϴ� ����� ���غ��ڸ�
 */
	  
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/************************************************************************
		 * �������� 
		 ***********************************************************************/
		int N = sc.nextInt();
		int[] arrayInt = new int [N];
		int sum = 0 ;
		int result = 0;
		/************************************************************************
		 * ������ �ֱ�
		 ***********************************************************************/
		for(int i = 0 ; i < N  ; i++) {
			arrayInt[i] = i+1;
		}
		
		/************************************************************************
		 * ������ ã��
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
