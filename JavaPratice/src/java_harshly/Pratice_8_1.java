package java_harshly;

import java.util.Scanner;

public class Pratice_8_1 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("�̸��� �Է��ϼ���: ");
		String name = sc.nextLine();
		System.out.print("���̸� �Է��ϼ���: ");
		int age  = sc.nextInt();
		System.out.printf("����� ���̴� %d�� �̰� �̸��� "+"%S"+"�Դϴ�.",age,name);
		
	}

}
