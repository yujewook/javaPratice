package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2439 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		// ��
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j < N - i - 1) {
					System.out.print(" ");
				} else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		sc.close();
	}
}
