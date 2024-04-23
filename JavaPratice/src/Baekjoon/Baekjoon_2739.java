package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2739 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int array[][] = new int [a][b];
		int array2[][] = new int [a][b];
		
		for(int i = 0 ; i < a ;i++) {
			for(int j = 0 ; j < b ;j++) {
				array [i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0 ; i < a ;i++) {
			for(int j = 0 ; j < b ;j++) {
				array2 [i][j] = sc.nextInt();
			}
		}
		sc.close();
		for(int i = 0 ; i < a ;i++) {
			for(int j = 0 ; j < b ;j++) {
				array [i][j] = array [i][j] + array2 [i][j] ;
				System.out.print(array[i][j] + " ");
			}
			 System.out.println();
		}
		
		
	}

}
