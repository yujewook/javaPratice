package java_harshly;

import java.util.Scanner;

public class Pratice_9_2 {
/*
 * 1. �ʸ� �Է� �޴´�
 * 2. ���� �ʷ� ����� ���� ����1, ���� �ʷ� ����� ���� ����2�� �Ҵ��Ѵ�
 * 3. �Է� ���� �ʸ� ����1�� �������� ���� �ð��� �Ҵ� 
 * 4. �������� ������ ����� ����2�� �Ҵ� �������� �ʿ� �Ҵ� �Ѵ� 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		int hour = 60*60;
		int min = 60;
		System.out.printf("%d �ʴ� %02d�ð� %02d�� %02d���Դ�." , input, input/hour, (input % hour)/min , input % min);
	}

}
