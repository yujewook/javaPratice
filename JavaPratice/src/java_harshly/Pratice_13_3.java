package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_3 {
/*
 * 선택 정렬을 이용한 오름차순
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arrayInt[] = {10,20,30,40,50};
		int max = 0; // 최소값
		int tmp = 0; // 교환값
		for (int i = 0 ; i < arrayInt.length ; i++ ) {
			max = arrayInt[i]; //10
			for(int j = i+1 ; j <= arrayInt.length -1 ; j++ ) {
				if ( arrayInt[i] < arrayInt[j]) {
					tmp = arrayInt[i];
					arrayInt[i] = arrayInt[j]; 
					arrayInt[j] = tmp;
				}
			}
		}
		
		System.out.print(Arrays.toString(arrayInt));
	}
}