package java_harshly;

import java.util.Scanner;

public class Pratice_10_2 {
/*
 * 1. 입력값 받기
 * 2. 80점 이상이면 합격 불합격
 * 3. 3항연산자 사용 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("점수입력 : ");
		int grade = sc.nextInt();
		System.out.print("결과 : " + (grade >= 80 ? "합격" : "불합격")  );
		
	}

}
