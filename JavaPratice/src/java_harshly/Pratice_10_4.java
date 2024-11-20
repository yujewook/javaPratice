package java_harshly;

import java.util.Scanner;

public class Pratice_10_4 {
/* 토너먼트 방식
 * 1. 입력값 받기
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int first = sc.nextInt();
		int seconde = sc.nextInt();
		int third = sc.nextInt();
		int max = first > seconde ? first : seconde;
		max = third > max ? third : max;
		
		System.out.println(max);
	}

}
