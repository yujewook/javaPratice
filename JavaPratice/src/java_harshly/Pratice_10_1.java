package java_harshly;

import java.util.Scanner;

public class Pratice_10_1 {
	/*
	 * 1. 입력값 받기
	 * 2. 변수 두개를 사용
	 * 3. 더하기 빼기 연산자 사용 안하고 비트 연산자로 계산해보기 
	 */
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.printf("%d", a+(~b+1));
			
		}

}
