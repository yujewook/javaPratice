package java_harshly;

import java.util.Scanner;

public class Pratice_11_1 {
	/*
	 * 1. 입력값 받기
	 * 2. if문으로 비교 서바이벌 형식
	 */
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			int max = input;
			input = sc.nextInt();
			if( input > max ) {
				max = input;
			}
			input = sc.nextInt();
			if( input > max ) {
				max = input;
			}
			System.out.println(max);
		}

}
