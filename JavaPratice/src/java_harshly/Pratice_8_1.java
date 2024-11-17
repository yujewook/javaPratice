package java_harshly;

import java.util.Scanner;

public class Pratice_8_1 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.nextLine();
		System.out.print("나이를 입력하세요: ");
		int age  = sc.nextInt();
		System.out.printf("당신의 나이는 %d살 이고 이름은 "+"%S"+"입니다.",age,name);
		
	}

}
