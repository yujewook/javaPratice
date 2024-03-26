package Baekjoon;

import java.util.HashSet;

public class Baekjoon_5597 {

	public static void main(String[] args) {
		int arr[] = new int[30];
		
		for(int i = 0 ; i <30; i++) {
			arr[i] = 0;
		}
		/*
		int comparison [] = new int [28]; 
		
		for(int i = 0 ; i < arr.length-2 ;i++) {
			int ran = (int)( Math.random() * 30 ) + 1 ;
			comparison[i] = ran;
			
			for(int j : comparison ) {
				System.out.println("j"+j);
				for(int k = 1 ; k<comparison.length ;k++) {
					if(comparison[k]!= j) {
						arr[ran]=1;
					}
				}
				
			}
			
		}
		

		*/
		HashSet<Integer> set = new HashSet<>();
		
		for (int i = 0 ; i<arr.length-2;i++) {
			int ran ;
			do {
				ran = (int) (Math.random() *30)+1;
		    } while(set.contains(ran));
			set.add(ran);
			arr[ran - 1] = 1; // 인덱스는 0부터 시작하므로 1을 빼줍니다.
		}
		
		int j = 1;
		for(int i : arr) {
			
			if(i == 0) {
				System.out.println(j);
			}
			j++;
		}
		
		
	}

}
