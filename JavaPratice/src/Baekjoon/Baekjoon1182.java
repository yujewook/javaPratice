package Baekjoon;

import java.util.Scanner;

public class Baekjoon1182 {
    /*
     * ����: ���� 1182�� - �κм����� ��
     * ��ũ: https://www.acmicpc.net/problem/1182
     *
     * ���� ���:
     * - ũ�Ⱑ N�� ������ �־����� ��,
     * - �� �κ� ���� �߿��� ���ҵ��� ���� S�� �Ǵ� ����� ���� ���϶�.
     * - �������� �����Ѵ�.
     *
     * �Է�:
     * - ù° ��: ���� N, S (1 �� N �� 20, |S| �� 1,000,000)
     * - ��° ��: N���� ������ �̷���� ���� (�� ������ ������ 100,000 ����)
     *
     * ���:
     * - ���� S�� �Ǵ� �κ� ������ ������ ���
     *
     * ���� �Է�:
     * 5 0
     * -7 -3 -2 5 8
     *
     * ���� ���:
     * 1
     *
     * ���� ����:
     * - ���Ʈ����, ��Ʈ��ŷ, DFS, �κ����� Ž��
     *
     * Ǯ�� ����:
     * - �� ���Ҹ� �������� ������ �����ϴ� ��� ����� ���� Ž��
     * - ���(�Ǵ� DFS)�� ����
     * - ��������� ���� S�� �� ��� count ����
     * - �������� �����ϹǷ�, �κ������� ���� ������ 0�� ���� ����
     */
    static int COUNT; 
    static int S;
    static int[] Intarray;
    static int N;

    // ���� �Լ�
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

        // ���� ���� 1~N���� ��� �õ�
        for (int cnt = 1; cnt <= N; cnt++) {
            combination(0, 0, cnt, 0);
        }

        System.out.println(COUNT);
    }
}
