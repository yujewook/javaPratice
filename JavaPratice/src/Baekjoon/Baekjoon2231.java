package Baekjoon;

import java.util.Scanner;
/*
문제: https://www.acmicpc.net/problem/2231
문제 제목: 백준 2231번 - 분해합

문제 요약:
어떤 자연수 N이 있을 때, N = 생성자 + 생성자의 각 자릿수 합.
이 때 N의 가장 작은 생성자를 구하라. 없으면 0 출력.

예제 입력 1:
216

예제 출력 1:
198

예제 입력 2:
100

예제 출력 2:
0
*/

class Baekjoon2231 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = 0;

        for (int i = 1; i < N; i++) {
            int sum = i;
            int temp = i;

            // 자릿수 합 계산
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

            // 분해합이 N과 같으면 생성자 찾음
            if (sum == N) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}


