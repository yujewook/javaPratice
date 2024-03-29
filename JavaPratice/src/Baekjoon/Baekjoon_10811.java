package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10811 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int arr[] = new int[N];
		
		for(int i = 0 ; i < arr.length ; i++ ) {
			arr[i] = i+1;
		}
		//¹Ù²Ù´Â È½¼ö
		int M = sc.nextInt();
		for(int z = 0 ; z < M ; z++ ) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			while( i < j ) {
				int tmp = arr[i-1];
				arr[i-1] = arr[j-1];
				arr[j-1] = tmp;
				i++;
				j--;
			}
		}
		sc.close();
		for(int i = 0 ; i < arr.length ; i++ ) {
			System.out.print(arr[i]+" ");
		}
		

	}

}
