package java_harshly;

import java.util.Scanner;

public class Pratice_9_1 {
/*
 * 1.두수를 입력 받는다.
 * 2.두수의 평균값을 받는다. 
 * 3.출력 
 */
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("두 정수를 입력해 주세요");
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		System.out.printf("avg : %.2f" , ((a+b)/2.0));
	}

}
