package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10952 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = 0;
		int b = 0;
		int result = 0; 
		while(true){
			a = sc.nextInt();
			b = sc.nextInt();
			result = a + b ;
			if (result == 0) break; 
			System.out.println(a + b);
		}
			
		
		sc.close();
	}

}
/*Á¤´ä 
 * public static void main(String[] args) {
 
		Scanner sc = new Scanner(System.in);
		int a = 0;
		int b = 0;
	
		
		while(true){
			a = sc.nextInt();
			b = sc.nextInt();
			if (a == 0 && b ==0) break;
			System.out.println(a+b);
		}
			
		
		sc.close();
	}*/