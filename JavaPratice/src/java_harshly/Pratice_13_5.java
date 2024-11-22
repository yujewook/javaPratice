package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_5 {
/*
 *  사각형에 숫자 채우기
 */
	public static void main(String[] args) {

		int arrayInt[][] = new int [5][5];
		int cnt = 1;
		for( int i = 0 ; i < arrayInt.length ; i++ ) {//열늘리기
			for( int j = 0 ; j < arrayInt.length ; j++ ) {//열늘리기
				arrayInt[i][j] = cnt++;
			}
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}