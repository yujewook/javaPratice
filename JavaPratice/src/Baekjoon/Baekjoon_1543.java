package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1543 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arrLength = sc.nextInt();
		int arr[] = new int[arrLength];
		
		for(int i = 0 ; i < arr.length ; i++) {
			arr[i] = sc.nextInt();
		}
		
		int max = 0 ;
		
		for(int i = 0 ; i < arr.length ; i++) {
			
			if( max < arr[i] ) {
				max = arr[i];
			}
			
		}
		System.out.println(max);
		double sum = 0;
		
		for(int i = 0 ; i < arr.length ; i++) {
			double point = ((double) arr[i] /max)*100 ;
			System.out.println(point);
			sum = sum + point; 
		}
		
		System.out.println("합계 ::::: "+sum);
		System.out.println("길이 ::::: " +arrLength);
	    System.out.println(sum/arrLength);	
	}

}
