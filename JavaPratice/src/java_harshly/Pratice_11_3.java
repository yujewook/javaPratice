package java_harshly;

import java.util.Scanner;

public class Pratice_11_3 {
	/*
	 * 1. �⺻��� 1000�� ���� final
	 * 2. ���̿� ���� ������ ����� ���� ����
	 * 3. �б� ó��   
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
		
		System.out.printf("���� %d, �������: %f",age,fee);
		
		//rate �� ��ȯ �ϰ� �״�� 
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
		System.out.printf("���� %d, �������: %f",age, charge*rate);
		
	}

}
