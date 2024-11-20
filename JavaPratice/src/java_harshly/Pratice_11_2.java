package java_harshly;

import java.util.Scanner;

public class Pratice_11_2 {
	/*
	 * 1. 기본요금 1000원 변수  
	 * 2. 20세 미만인 그룹 25프로 할인 === 기본요금*(1-0.25)
	 * 3. 20세 이상 미할인
	 * 4. 20세이상 은 전부 나이는 20으로 출력
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double charge = 1000;
		
		int age = sc.nextInt();
		if ( age < 20) {
			charge = charge * (1 - 0.25) ;
		}
		if( age >= 20 ) {
			age = 20;
		}
		
		System.out.printf("나이 %d, 최종요금: %f",age,charge);
	}

}
