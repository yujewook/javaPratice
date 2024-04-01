package Baekjoon;

import java.util.Scanner;

public class Baekjoon_9086 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		
		String array[] = new String[testCase];
		
		for(int i = 0 ; i < testCase ; i++ ) {
			StringBuilder stringCase = new StringBuilder();
			String a = sc.next();

			stringCase.append(a.charAt(0)).append(a.charAt(a.length()-1));
			array[i] = stringCase.toString();
			
		}
		sc.close();
		//Ãâ·Â
		for(int i = 0 ; i < testCase ; i++ ) {
			System.out.println(array[i]);
		}
		
		
	}

}
