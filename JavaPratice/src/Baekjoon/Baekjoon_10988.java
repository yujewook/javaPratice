package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10988 {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		String array [] = new String [result.length()];   
		
		for(int i = 0; i < result.length(); i++) {
			array[i] =String.valueOf(result.charAt(i)) ;
		}
		
		int flage = 0;
		
		
		for(int i = 0; i < result.length(); i++) {
			if(array[i].equals(array[array.length-i-1])) {
				flage = 1;
			} else {
				flage = 0;
				break;
			}
		}
		
		System.out.print(flage);

	}

}
