package java_harshly;

import java.util.Scanner;

public class Pratice_10_1 {
	/*
	 * 1. �Է°� �ޱ�
	 * 2. ���� �ΰ��� ���
	 * 3. ���ϱ� ���� ������ ��� ���ϰ� ��Ʈ �����ڷ� ����غ��� 
	 */
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.printf("%d", a+(~b+1));
			
		}

}
