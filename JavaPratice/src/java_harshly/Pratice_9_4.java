package java_harshly;

import java.util.Scanner;

public class Pratice_9_4 {
/*
 * 1. �Է°� �ޱ�
 * 2. ���� �ΰ��� ���
 * 3. �������� , �Է�����Ǵ� ���� �ϳ��� �̿� 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�����Է�");
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
