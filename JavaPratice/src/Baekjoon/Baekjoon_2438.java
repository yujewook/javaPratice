package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2438 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//ÁÙ°¹¼ö
		for( int i = 1 ; i <= N ; i++) {
			// º°°¹¼ö
			for(int j = 1 ; j <= i ; j++) {
				String a = "*";
				System.out.print(a);
			}
			System.out.println("");			
		}


	}

}
