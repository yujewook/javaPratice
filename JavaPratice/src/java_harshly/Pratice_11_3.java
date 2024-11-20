package java_harshly;

import java.util.Scanner;

public class Pratice_11_3 {
	/*
	 * 1. 기본요금 1000원 변수 final
	 * 2. 나이에 따라 할인율 적용시 사용될 변수
	 * 3. 분기 처리   
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final double charge = 1000;
		int age = sc.nextInt();
		double fee = 0;
		if( age <= 3 && age >= 0 ) {
			fee = 0;
		} else if( age<=13 && age > 3) {
			fee =  charge*(1.0 - 0.5) ;
		}  else if( age<=19 && age > 13) {
			fee =  charge*(1.0 - 0.25) ;
		} else {
			fee =  charge;
		}
		
		System.out.printf("나이 %d, 최종요금: %f",age,fee);
		
		//rate 만 변환 하고 그대로 
		double rate = 0.0;
		if( age <= 3 && age >= 0 ) {
			rate = 0.0;
		} else if( age<=13 && age > 3) {
			rate =  1.0 - 0.5 ;
		}  else if( age<=19 && age > 13) {
			rate = 1.0 - 0.25 ;
		} else  {
			rate =  1.0;
		}
		System.out.println();
		System.out.printf("나이 %d, 최종요금: %f",age, charge*rate);
		
	}

}
