package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 아홉 난쟁이 중 일곱 명의 키의 합이 100이 되도록 그 일곱 명을 오름차순으로 출력하라. 
 * input    20
			7
			23
			19
			10
			15
			25
			8
			13
  output    7
			8
			10
			13
			19
			20
			23
 */

class Baekjoon2309 {
	static int [] heightOutput = new int[7];
	static int [] height = new int [9];
	static boolean find = false;
	static void combination(int start , int depth) {
		if(find)
			return;
		if (depth == 7) {
            if (validation()) {
                Arrays.sort(heightOutput);
                for (int h : heightOutput) {
                    System.out.println(h);
                }
                find = true;
            }
            return;
        }
		for( int i = start ; i < height.length ; i++ ) {
			heightOutput[depth] = height[i];
			combination(i+1, depth+1);
		}
	}
	
	static boolean validation() {
		int sum = 0;
		for(int i = 0 ; i < heightOutput.length ;i++) {
			sum += heightOutput[i];
		}
		if(sum == 100) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    for(int i = 0 ; i< height.length ;i++) {
	    	height[i] = sc.nextInt();
	    }
	    combination(0,0);
	    Arrays.sort(heightOutput);
        for (int h : heightOutput) {
            System.out.println(h);
        }
	    
	}


	
}


