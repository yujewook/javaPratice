package java_harshly;

import java.util.Scanner;

public class Pratice_9_3 {
/*
 * 1. 입력값 받기
 * 2. 입력값 변수 두개에 넣기
 * 3. tmp를 사용해서 두변수 자리 교환
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 두개 입력");
		int a = sc.nextInt();
		int b = sc.nextInt();
		int tmp = 0;
		tmp = a;
		a = b;
		b = tmp;
		System.out.printf("a: %d b: %d",a,b);
	}

}
