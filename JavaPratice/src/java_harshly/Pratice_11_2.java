package java_harshly;

import java.util.Scanner;

public class Pratice_11_2 {
	/*
	 * 1. �⺻��� 1000�� ����  
	 * 2. 20�� �̸��� �׷� 25���� ���� === �⺻���*(1-0.25)
	 * 3. 20�� �̻� ������
	 * 4. 20���̻� �� ���� ���̴� 20���� ���
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
		
		System.out.printf("���� %d, �������: %f",age,charge);
	}

}
