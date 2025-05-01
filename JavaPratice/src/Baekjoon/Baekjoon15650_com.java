package Baekjoon;

import java.util.ArrayList;
import java.util.List;

public class Baekjoon15650_com {

    static int[] array;
    static int N;
    static int M;
    static List<Integer> output = new ArrayList<>();

    static void combination(int depth, int start) {
        if (depth == M) {
            for (int num : output) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            output.add(array[i]); // ���� �� �߰�
            combination(depth + 1, i + 1); // ���� ���� Ž��
            output.remove(output.size() - 1); // �� ��Ʈ��ŷ: �ǵ��ư���
        }
    }

    public static void main(String[] args) {
        N = 4;
        M = 2;
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = i + 1;
        }

        combination(0, 0);
    }
}
