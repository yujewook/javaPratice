package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10807 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int array[] = new int[N];
		
		for (int a = 0; a < array.length ; a++ ) {
			array[a] = sc.nextInt();
		}
		
		int input = sc.nextInt();
		int result = 0;
		
		for (int a = 0; a < array.length ; a++ ) {
			if (array[a] == input ) {
				result++;
			}
		}
		sc.close();
		System.out.println(result);
	}
}
