package Baekjoon;

import java.util.Scanner;

public class Baekjoon2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.close();
		
		//StringBuilder a = new StringBuilder("long");
		
		String result = "int";
		
		if( N >= 4 ) {
			StringBuilder b = new StringBuilder("");
			for (int i = 0 ; i < N/4 ; i++ ) {
//				b.append(a);
				b.append(new StringBuilder("long"));
			}
			System.out.println(b + result);
			
		} else {
			System.out.println("int");
		}
		

	}
	
	
	/*
	 * Á¤´ä
	 *  Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		String str = "";
		scan.close();
		
		for(int i=1; i<=n/4; i++) {
			str += "long ";
		}
		System.out.println(str + "int");
	 * */

}

