package Baekjoon;

import java.util.Scanner;

/*
 * 문제: 백준 10818번 - 최소, 최대
 * 링크: https://www.acmicpc.net/problem/10818
 *
 * 문제 요약:
 * - N개의 정수를 입력받아 최솟값과 최댓값을 출력하는 문제.
 * - 정렬 없이 반복문을 사용하여 직접 최솟값, 최댓값을 찾아야 한다.
 * 
 * 입력:
 * - 첫째 줄: 정수 N (1 ≤ N ≤ 1,000,000)
 * - 둘째 줄: N개의 정수 (각각 -1,000,000 이상 1,000,000 이하)
 * 
 * 출력:
 * - 최솟값과 최댓값을 공백으로 구분하여 출력
 * 
 * 예시:
 * 입력: 
 * 5
 * 20 10 35 30 7
 * 
 * 출력:
 * 7 35
 */
public class Baekjoon10818 {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int min = 1000001;
    	int max = -1000001;
    	//배열
    	int[] arrayInput = new int[N];
    	
    	for( int i = 0 ; i < arrayInput.length ; i++ ) {
    		arrayInput[i] = sc.nextInt();
    		//나라면 입력 받는 동시에 할듯 입력을 받고 바로 그전 값과 비교 하게 
    		if(i != 0 ) {
    			// 혹시나 안전성을 위해 max < arrayInput[i] 비교 앞이 ture max < arrayInput[i] 연산암함 
    			if ( max < arrayInput[i] ) {
    				max = arrayInput[i];
    			}
    			if ( min > arrayInput[i] ) {
    				min = arrayInput[i];
    			}
    		}
    	}
    	System.out.print(min+" "+max);//하지만 이걸 원하는 알고리즘이 아닐듯 싶다.
    	//초기화 부분
    	min = 1000001;
    	max = 0;
    	for( int i = 0 ; i < arrayInput.length ; i++ ) {
    		if ( max < arrayInput[i] ) {
				max = arrayInput[i];
			}
			if ( min > arrayInput[i] ) {
				min = arrayInput[i];
			}
    	}
    	System.out.print("\t");
    	System.out.print(min+" "+max);//하지만 이걸 원하는 알고리즘이 아닐듯 싶다.
    }

}
