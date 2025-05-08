package Baekjoon;
//백준 1010번 - 다리 놓기
//문제 설명:
//서쪽에 N개의 사이트, 동쪽에 M개의 사이트가 있을 때,
//서쪽 사이트 N개를 모두 서로 다른 동쪽 사이트에 연결하는 경우의 수를 구하는 문제
//조건: 한 사이트에는 최대 한 개의 다리만 연결할 수 있다 (중복 불가, 순서 상관 없음)

//입력 예시:
//3           ← 테스트 케이스 수
//2 2         ← N=2, M=2 → 결과: 1
//1 5         ← N=1, M=5 → 결과: 5
//13 29       ← N=13, M=29 → 결과: 67863915

import java.util.Scanner;

public class Baekjoon1010 {
 static int dep = 0;
 static int count = 0;
 // 조합 함수: C(m, n)
 static int combination(int m, int n) {
     int result = 1;

     // 최적화: n > m-n일 경우, 대칭성 이용
     if (n > m - n) {
         n = m - n;
     }

     for (int i = 1; i <= n; i++) {
         result *= (m - i + 1);
         result /= i;
     }

     return result;
 }
 
 public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);
	 int t = sc.nextInt();
	 int n , m ;
	 for (int i = 0 ; i < t ; i++) {
		 n = sc.nextInt();
		 m = sc.nextInt();
		 System.out.println(combination(n,m));
	 }
	 

 }
 
}
