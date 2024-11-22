package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_2 {
/*
 * 버블 정렬을 이용한 오름차순
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arrayInt[] = {10,20,30,40,50};
		int tmp = 0;
		
		for (int i = 0 ; i < arrayInt.length; i++) {
			for(int j = 0 ; j < arrayInt.length-i-1;  j++) {
				if( arrayInt[j] < arrayInt[j+1]) {
					tmp = arrayInt[j];
					arrayInt[j] = arrayInt[j+1]; 
					arrayInt[j+1] = tmp;
				}
			}
		}
		System.out.print(Arrays.toString(arrayInt));
	}
}