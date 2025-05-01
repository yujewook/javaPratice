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
            output.add(array[i]); // 현재 값 추가
            combination(depth + 1, i + 1); // 다음 깊이 탐색
            output.remove(output.size() - 1); // ★ 백트래킹: 되돌아가기
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
