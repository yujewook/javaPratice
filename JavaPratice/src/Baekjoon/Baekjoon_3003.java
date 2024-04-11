package Baekjoon;

import java.util.Scanner;

public class Baekjoon_3003 {

	public static void main(String[] args) {
		int [] array = new int[6];
		int [] array2 = {1,1,2,2,2,8};
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0 ; i < array.length ; i++) {
			array[i] = sc.nextInt();
			array[i]=array2[i]-array[i];
			
		}
		for(int i = 0 ; i < array.length ; i++) {
			System.out.print(array[i]+ " ");
		}
		
		
	}

}
