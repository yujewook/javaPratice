package Practice_Class;

import java.util.Scanner;

//n�� �־����� ��, 1���� n���� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.
//ù° �ٿ� n (1 �� n �� 10,000)�� �־�����.

class Bacjun {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int result = 0;
		for(int i = 1 ; i <= a ;i++) {
			result =+ i;
		}
		System.out.println(result);
	}
}


