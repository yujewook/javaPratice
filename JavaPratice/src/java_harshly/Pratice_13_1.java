package java_harshly;

import java.util.Scanner;

public class Pratice_13_1 {
/*
 * 최대값 찾기
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arrayInt[] = new int[5];
		int max = 0;
		for(int i = 0 ; i < arrayInt.length ; i++) {
			arrayInt[i] = sc.nextInt();
			if( max < arrayInt[i]) {
				max = arrayInt[i];
			}
		}
		sc.close();
		System.out.print("max : "+max);
	}
}