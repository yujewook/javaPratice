package Baekjoon;

import java.util.Scanner;

public class Baekjoon_11022 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0 ; i < T ; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			System.out.println("Case #" + i + ": " + A + " + " + B + " = " + (A+B));
		}
		sc.close();
		
		
		

	}

}
