package java_harshly;

import java.util.Scanner;

public class Pratice_9_3 {
/*
 * 1. �Է°� �ޱ�
 * 2. �Է°� ���� �ΰ��� �ֱ�
 * 3. tmp�� ����ؼ� �κ��� �ڸ� ��ȯ
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� �ΰ� �Է�");
		int a = sc.nextInt();
		int b = sc.nextInt();
		int tmp = 0;
		tmp = a;
		a = b;
		b = tmp;
		System.out.printf("a: %d b: %d",a,b);
	}

}
