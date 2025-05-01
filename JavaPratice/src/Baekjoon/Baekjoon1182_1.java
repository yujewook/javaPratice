package Baekjoon;

import java.util.ArrayList;
import java.util.List;


public class Baekjoon1182_1 {
	static int[] array = {1, 2, 3, 4};

	public static void main(String[] args) {
		for (int r = 1; r <= array.length; r++) {
			List<Integer> output = new ArrayList<>();
			combination(0, 0, r, output);
		}
}

static void combination(int depth, int start, int r, List<Integer> output) {
    if (depth == r) {
        for (int num : output) {
            System.out.print(num + " ");
        }
        System.out.println();
        return;
    }

    for (int i = start; i < array.length; i++) {
        output.add(array[i]);
        combination(depth + 1, i + 1, r, output);
        output.remove(output.size() - 1); // 백트래킹
    }
}
}
