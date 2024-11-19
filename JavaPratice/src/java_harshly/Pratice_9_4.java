package java_harshly;

import java.util.Scanner;

public class Pratice_9_4 {
/*
 * 1. 입력값 받기
 * 2. 변수 두개를 사용
 * 3. 누적변수 , 입력저장되는 변수 하나를 이용 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자입력");
		int input = sc.nextInt();
		int output = 0;
		output += input;
		input = sc.nextInt();
		output += input;
		input = sc.nextInt();
		output += input;
		System.out.printf("total "+output);
	}

}
