package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2018_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int start = 1, end = 1, sum = 1, result = 0;

        while (end <= N) {
            if (sum == N) {
                result++;
            }
            if (sum >= N) {
                sum -= start;
                start++;
            } else {
                end++;
                sum += end;
            }
        }
        System.out.println(result);
    }
}
