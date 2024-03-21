package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_10871 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int x = sc.nextInt();
		int[] A = new int[N];
		
		for(int b = 0 ; b < A.length; b++) {
			A[b] = sc.nextInt();
		}
		
		for(int b = 0 ; b < A.length; b++) {
			if (A[b] > x) {
				int c = A[b]; 
				A = Arrays.stream(A)
				         .filter(item -> item != c)
				         .toArray();
			}
		}
		
		for(int b = 0 ; b < A.length; b++) {
			System.out.println(A[b]);
		}
		
		
		
		
	}
}

/*
//Á¤´ä
import java.util.Scanner;

public class Main {
 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int X = in.nextInt();
        
		StringBuilder sb = new StringBuilder();
 
		for(int i = 0 ; i < N ; i++) {
			
			int value = in.nextInt();
			if(value < X) {
				sb.append(value+" ");
			}
		}
		System.out.println(sb);	
	}
}
*/
