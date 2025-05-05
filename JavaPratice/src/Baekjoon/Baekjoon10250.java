package Baekjoon;

import java.util.Scanner;

/**
 * 문제: 백준 10250번 - ACM 호텔
 * https://www.acmicpc.net/problem/10250
 *
 * 문제 설명:
 * - 호텔은 H층, 각 층에 W개의 방이 있음 (방 번호는 YYXX 형식: YY는 층수, XX는 호수)
 * - 손님은 1번부터 차례대로 가장 가까운 층부터 차례대로 방을 배정받음 (1층 → H층 → 1층 → ...)
 * - N번째 손님에게 배정되는 방 번호를 구하라
 *
 * 입력:
 * - 첫 줄: 테스트 케이스 수 T
 * - 이후 T줄: 각 줄에 H W N이 주어짐
 *
 * 출력:
 * - 각 테스트 케이스마다 N번째 손님의 방 번호 출력 (YYXX 형태, XX는 항상 2자리)
 *
 * 예제 입력:
 * 2
 * 6 12 10
 * 30 50 72
 *
 * 예제 출력:
 * 402
 * 1203
 *
 * 풀이 아이디어:
 * - 층수 = N % H (단, 나머지가 0이면 H층)
 * - 호수 = N / H (단, 나머지가 0이면 그대로, 아니면 +1)
 * - 층수와 호수를 YYXX 형태로 출력 (호수는 항상 2자리로 출력해야 함)
 */
public class Baekjoon10250 {
	 public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 int T = sc.nextInt();
		 
		 while(T!=0) {
	         int H = sc.nextInt(); // 층 수
	         int W = sc.nextInt(); // 각 층 방 수 (사용 안 함)
	         int N = sc.nextInt(); // N번째 손님
			 
			 int floor = 0;
			 int room = 0;
			 if ( N%H != 0 ) {
				 room = (N/H)+1;
				 floor = N % H;
			 } else {
				 floor = H;
				 room = N / H;
			 }
			 
			 System.out.printf("%d%02d\n", floor, room);
			 T--;
		 }
	 }
}
