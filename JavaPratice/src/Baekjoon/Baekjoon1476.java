package Baekjoon;

import java.util.Scanner;

/**
 * 문제: 백준 1476번 - 날짜 계산
 * 링크: https://www.acmicpc.net/problem/1476
 * 
 * 문제 요약:
 * - 지구(E), 태양(S), 달(M)의 연도는 각각 15, 28, 19년을 주기로 반복된다.
 * - 입력으로 E, S, M이 주어질 때, 세 주기가 모두 일치하는 가장 빠른 연도를 구하라.
 * - 연도는 1년부터 시작하며, 각각의 값은 최대값을 넘으면 1로 순환된다.
 * 
 * 입력 예시:
 * 1 16 16
 * 
 * 출력 예시:
 * 5266
 * 
 * 풀이 전략:
 * - year를 1부터 하나씩 증가시키며 세 값이 모두 일치할 때까지 반복
 * - 각각의 값은 (값 - 1) % 주기 + 1 과 같음
 * - 최소공배수인 15 * 28 * 19 = 7980을 넘기지 않아도 해결 가능
 */
public class Baekjoon1476 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int E = sc.nextInt(); // 지구 시작연도 (1 ~ 15)
        int S = sc.nextInt(); // 태양 시작 연도 (1 ~ 28)
        int M = sc.nextInt(); // 달 시작 연도 (1 ~ 19)
        
        int year = 1;
        
        while(true) {
        	if( ((year-1)%15) +1 == E 
        	   && ((year-1)%28) +1 == S
        	   && ((year-1)%19) +1 == M) {
        		break;
        	}
        	year++;
        }
        System.out.println("E = " + E);
        System.out.println("S = " + S);
        System.out.println("M = " + M);
        System.out.println("YEAR "+year);

    
    }
}
