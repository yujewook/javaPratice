package Baekjoon;

import java.util.Scanner;
/*
����: https://www.acmicpc.net/problem/2231
���� ����: ���� 2231�� - ������

���� ���:
� �ڿ��� N�� ���� ��, N = ������ + �������� �� �ڸ��� ��.
�� �� N�� ���� ���� �����ڸ� ���϶�. ������ 0 ���.

���� �Է� 1:
216

���� ��� 1:
198

���� �Է� 2:
100

���� ��� 2:
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

            // �ڸ��� �� ���
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }

            // �������� N�� ������ ������ ã��
            if (sum == N) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}


