package Baekjoon;

import java.util.Scanner;

public class Baekjoon_11720 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		//гу
		int totalResult = 0;
		int [] arr = new int[N];
		
		for(int i = 0 ; i < N ; i++) {
			arr[i] = sc.nextInt();
			totalResult = arr[i] + totalResult;
		}
		
		System.out.print(totalResult);
		sc.close();
		
		
	}

}
