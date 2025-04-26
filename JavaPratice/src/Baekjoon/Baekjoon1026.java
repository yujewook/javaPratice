package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

/*
문제: https://www.acmicpc.net/problem/1026  
문제 제목: 백준 1026번 - 보물

문제 요약:
두 수열 A와 B가 주어졌을 때,  
다음과 같은 식으로 정의된 S의 **최솟값**을 구하는 문제이다.

S = A[0]×B[0] + A[1]×B[1] + ... + A[N-1]×B[N-1]

조건:
- 수열 A는 **재배열 가능**
- 수열 B는 **고정**

이때 S의 최솟값을 출력하라.

예제 입력:
5
1 1 1 6 0
2 7 8 3 1

예제 출력:
18

풀이 핵심:
- A는 오름차순 정렬
- B는 정렬하지 않고, A에서 작은 수부터 B의 큰 수에 대응하도록 매칭
*/
public class Baekjoon1026 {
	
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int[] A = new int[N];
    	int[] B = new int[N];
    	
    	for(int i = 0 ; i < A.length; i++) {
    		A[i] = sc.nextInt();
    	}
    	for(int i = 0 ; i < B.length; i++) {
    		B[i] = sc.nextInt();
    	}
    	
    	Arrays.sort(A);
    	int[] copy = Arrays.copyOf(B, B.length); //정렬
    	int sum = 0;//합
    	
    	for( int i = 0 ; i < A.length ; i++ ) {
    		int maxNum = -1;
    		int index = 0;
    		for( int j = 0 ; j < copy.length ; j++ ) {
    			if( maxNum <= copy[j] ) {
        			maxNum = copy[j];
        			index = j;  
        		}
        	}
    		copy[index] = 0; //불필요한 데이터 삭제
			sum += (A[i] * maxNum);
    	}
    	
    	System.out.println(sum);
	}
	
}


