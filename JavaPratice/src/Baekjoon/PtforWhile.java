package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
// 입력 예시:
// 216

// 출력 예시:
// 198

// 👉 풀이 힌트:
// i를 1부터 N까지 반복하면서
// i + i의 각 자리수 합이 N이 되는지 확인
// 조건 만족하면 그 i가 생성자
 */

class PtforWhile {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	//카드갯수
    	int n = 216;
    	int reult = 0;
    	for (int i = 1 ; i <n ;i++) {
    		int sum = 0;
    		while(i == 0) {
    			sum += i;
    			if(sum == n) {
    				reult = sum;
    				break;
    			}
    			i--;
    		}
    		
    	}

	}
	

}


