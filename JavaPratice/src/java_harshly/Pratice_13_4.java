package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_4 {
/*
 *  행과 열의 합을 구하기
 *  1. 전체 열 for문 
 *  2. 각열 을 더해주기
 *  3. 열의 끼리 더해질 값 세팅 하기 
 */
	public static void main(String[] args) {

		int arrayInt[][] = { {10,20,30,0}
						    , {40,50,60,0}
						    , {0,0,0,0}};
		int sum = 0;
		int colSum = 0;
		//행의합
		for(int i = 0 ; i < arrayInt[i].length -2 ; i++ ) {
			sum = 0 ; //행 값 위해 초기화
			for(int j = 0 ; j < arrayInt[i].length -1 ; j++ ) { 
				//행의 전체값 더하기 
				sum += arrayInt[i][j];
				arrayInt[i][arrayInt[i].length-1] = sum;
			}
		}
		//열의 합 
		for(int i = 0 ; i < arrayInt.length+1 ; i++ ) {
			colSum = 0;
			for(int j = 0 ; j < arrayInt.length-1 ; j++ ) {
				colSum += arrayInt[j][i];
				arrayInt[2][i] = colSum; 
			}
			
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}