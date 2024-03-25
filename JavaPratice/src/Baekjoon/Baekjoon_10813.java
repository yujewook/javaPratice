package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10813 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int arr [] = new int[N];
		
		for (int a = 0 ; a < arr.length ; a++) {
			arr[a] = a+1;
	
		}
		
		int M = sc.nextInt();

		for (int a = 0 ; a < M; a++) {
			int I = sc.nextInt();
			int J = sc.nextInt();
			int tmp = 0;
			
			tmp = arr[I-1];
			arr[I-1] = arr[J-1];
			arr[J-1] = tmp;
		}
		
		for(int k = 0 ; k < arr.length; k++) {
			System.out.print(arr[k] + " ");
		}
		

	}

}
