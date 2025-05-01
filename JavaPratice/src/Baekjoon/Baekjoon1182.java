package Baekjoon;

import java.util.Scanner;

public class Baekjoon1182 {
    /*
     * 문제: 백준 1182번 - 부분수열의 합
     * 링크: https://www.acmicpc.net/problem/1182
     *
     * 문제 요약:
     * - 크기가 N인 수열이 주어졌을 때,
     * - 그 부분 수열 중에서 원소들의 합이 S가 되는 경우의 수를 구하라.
     * - 공집합은 제외한다.
     *
     * 입력:
     * - 첫째 줄: 정수 N, S (1 ≤ N ≤ 20, |S| ≤ 1,000,000)
     * - 둘째 줄: N개의 정수로 이루어진 수열 (각 정수의 절댓값은 100,000 이하)
     *
     * 출력:
     * - 합이 S가 되는 부분 수열의 개수를 출력
     *
     * 예제 입력:
     * 5 0
     * -7 -3 -2 5 8
     *
     * 예제 출력:
     * 1
     *
     * 문제 유형:
     * - 브루트포스, 백트래킹, DFS, 부분집합 탐색
     *
     * 풀이 전략:
     * - 각 원소를 포함할지 말지를 선택하는 모든 경우의 수를 탐색
     * - 재귀(또는 DFS)로 구현
     * - 현재까지의 합이 S가 될 경우 count 증가
     * - 공집합은 제외하므로, 부분집합의 원소 개수가 0인 경우는 제외
     */
    static int COUNT; 
    static int S;
    static int[] Intarray;
    static int N;

    // 조합 함수
    static void combination(int depth, int start, int r, int sum) {
        if (depth == r) {
            if (sum == S) {
                COUNT++;
            }
            return;
        }

        for (int i = start; i < N; i++) {
            combination(depth + 1, i + 1, r, sum + Intarray[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        S = sc.nextInt();
        Intarray = new int[N];

        for (int i = 0; i < N; i++) {
            Intarray[i] = sc.nextInt();
        }

        // 조합 길이 1~N까지 모두 시도
        for (int cnt = 1; cnt <= N; cnt++) {
            combination(0, 0, cnt, 0);
        }

        System.out.println(COUNT);
    }
}
