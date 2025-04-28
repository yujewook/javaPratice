package Baekjoon;

import java.util.Scanner;

/*
 * 문제: 백준 20053번 - 최소, 최대 2
 * 링크: https://www.acmicpc.net/problem/20053
 *
 * 문제 요약:
 * - 여러 개의 테스트 케이스가 주어진다.
 * - 각 테스트 케이스마다 입력받은 정수들의 최솟값과 최댓값을 출력한다.
 * 
 * 입력:
 * - 첫째 줄에 테스트 케이스 개수 T가 주어진다. (1 ≤ T ≤ 1,000)
 * - 각 테스트 케이스마다:
 *     - 첫 줄: 정수 N (1 ≤ N ≤ 1,000)
 *     - 둘째 줄: N개의 정수 (각각 -1,000,000 이상 1,000,000 이하)
 * 
 * 출력:
 * - 각 테스트 케이스마다 최솟값과 최댓값을 한 줄에 출력
 * 
 * 예시 입력:
 * 2
 * 5
 * 20 10 35 30 7
 * 3
 * -10 0 10
 * 
 * 예시 출력:
 * 7 35
 * -10 10
 */
public class Baekjoon20053 {

    public static void main(String[] args) {
        // 여기에 코드 작성하면 돼
    	Scanner sc = new Scanner(System.in);
    	int testCase = sc.nextInt();
    	//테스트 케이스 생성을 위한 for문
    	for(int i = 0 ; i < testCase ; i++) {
    		//배열수 
    		int n = sc.nextInt();
    		int[] arrayInt = new int[n];
    		for( int j = 0 ; j < arrayInt.length ; j++ ) {
    			arrayInt[j] = sc.nextInt(); 
    		}
    		int max = -1000001;
    		int min = 1000001;
    		for( int j = 0 ; j < arrayInt.length ; j++ ) {
    			if ( min > arrayInt[j]) {
    				min = arrayInt[j];
    			}
    			if ( max < arrayInt[j] ) {
    				max = arrayInt[j];
    			}
    		}
    		System.out.println(min + " " + max);
    	}
    	
    }

}
