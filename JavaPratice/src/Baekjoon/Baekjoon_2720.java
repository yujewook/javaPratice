package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2720 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력갯수
		int T = sc.nextInt();
		
		//숫자 배열
		int [] chargeArray = new int[T];
		
		for(int i = 0 ; i < chargeArray.length ;i++) {
			chargeArray[i] = sc.nextInt(); 
		}
		sc.close();
		
		for(int i = 0 ; i < chargeArray.length ;i++) {
		    int quarter = chargeArray[i] / 25;
		    chargeArray[i] %= 25;
		    
		    int dime = chargeArray[i] / 10;
		    chargeArray[i] %= 10;

		    int nickel = chargeArray[i] / 5;
		    chargeArray[i] %= 5;

		    int penny = chargeArray[i];
		    System.out.println(quarter + " " + dime + " " + nickel + " " + penny);
		}
		
	}

}
