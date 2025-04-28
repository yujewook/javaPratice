package Baekjoon;

import java.util.Scanner;

/*
 * 문제: 백준 1436번 - 영화감독 숌
 * 링크: https://www.acmicpc.net/problem/1436
 *
 * 문제 요약:
 * - "666"이 들어가는 수들을 작은 순서대로 나열했을 때
 * - N번째에 오는 수를 출력한다.
 *
 * 입력:
 * - 첫째 줄: 정수 N (1 ≤ N ≤ 10,000)
 *
 * 출력:
 * - N번째 종말의 수를 출력
 *
 * 예시 입력:
 * 2
 *
 * 예시 출력:
 * 1666
 */
public class Baekjoon1436 {

    public static void main(String[] args) {
        // 여기에 코드 작성하면 돼
    	Scanner sc = new Scanner(System.in);
    	int rank = 2; // sc.nextInt();
    	String num = "666";
    	int flag = 0;
    	int a = 0;
    	for (int N = 1 ; N < 10001 ; N++) {
 
    		String ex = String.valueOf(N);
    		if (num.contentEquals(ex)) {
    			flag++;
    			if(flag == rank) {
    				break;
    			}
    		};
    	}
    }

}
