package Baekjoon;

import java.util.Scanner;

/**
 * 문제: 백준 2839번 - 설탕 배달
 * 링크: https://www.acmicpc.net/problem/2839
 * 
 * 문제 요약:
 * - N킬로그램의 설탕을 배달해야 함.
 * - 설탕은 3kg 또는 5kg 봉지로만 포장되어 있음.
 * - 정확하게 Nkg을 만들기 위해 필요한 최소 봉지 수를 구하라.
 * - 만약 정확히 만들 수 없다면 -1을 출력한다.
 * 
 * 입력:
 * - 첫째 줄에 설탕의 무게 N (3 ≤ N ≤ 5000)
 * 
 * 출력:
 * - 설탕을 정확히 N킬로그램 배달할 때 필요한 최소 봉지 수.
 * - 정확하게 만들 수 없다면 -1을 출력.
 */

public class Baekjoon2839 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // ===== 기존 코드 (오류가 있었던 버전) =====
        int n = sc.nextInt(); // 설탕 무게 입력
        double count = 0;
        String num = "";

        if (n % 3 != 0 && n % 5 != 0) {
            System.out.println(-1);
        }
        
        if (n / 5 > 0) {
            count = Math.ceil((double)n / 5);
            n = n % 5;
        }
        if (n % 3 != 0) {
            System.out.println(-1);
        } else {
            count += Math.ceil((double)n / 3);
            System.out.println((int)count);
        }

        // ===== 정답 코드 (수정된 버전) =====
        /*
        int n = sc.nextInt(); // 설탕 무게 입력
        int count = 0;

        while (true) {
            // 5kg 봉지로 채운 후 남은 양이 3으로 나누어떨어지면 OK
            if (n % 5 == 0) {
                count += n / 5;
                System.out.println(count);
                break;
            }
            // 5로 나누어떨어지지 않으면 3kg 봉지를 하나 추가로 사용
            n -= 3;
            count++;

            // n이 음수가 되면 정확히 만들 수 없는 경우
            if (n < 0) {
                System.out.println(-1);
                break;
            }
        }
        */
    }
}