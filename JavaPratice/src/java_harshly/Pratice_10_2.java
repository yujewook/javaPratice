package java_harshly;

import java.util.Scanner;

public class Pratice_10_2 {
/*
 * 1. �Է°� �ޱ�
 * 2. 80�� �̻��̸� �հ� ���հ�
 * 3. 3�׿����� ��� 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�����Է� : ");
		int grade = sc.nextInt();
		System.out.print("��� : " + (grade >= 80 ? "�հ�" : "���հ�")  );
		
	}

}
