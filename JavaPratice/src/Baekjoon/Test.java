package Baekjoon;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int count = 0;
		String num ="666";
		int result = 0;
		for(int i =1 ; i <= 10000 ; i++ ) {
			if (String.valueOf(i).contains(num)) {
				count++;
				if (count == n) {
					result = i;
					break;
				}
			}
		}
		System.out.println(result);
	}
}
