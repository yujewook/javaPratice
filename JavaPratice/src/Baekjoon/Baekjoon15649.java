package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon15649 {
	/**
	 * 클래스명: Baekjoon15649
	 * 문제: 백준 15649번 - N과 M (1)
	 * 링크: https://www.acmicpc.net/problem/15649
	 *
	 * ✅ 문제 설명:
	 * - 1부터 N까지 자연수 중에서 **중복 없이 M개를 고른 모든 순열**을 출력
	 * - 백트래킹과 visited 배열을 활용한 완전탐색
	 *
	 * ✅ 핵심 포인트:
	 * - 순열(Permutation)
	 * - visited[]로 중복 방지
	 * - depth로 선택한 수 개수 추적
	 */
	static int N;
	static int M;
	static int[] a;
	static int[] output;
	static boolean[] visited; // 각 숫자 사용 여부
	
	static void combination(int depth , int start) {
		if (depth == M) {
			System.out.print(Arrays.toString(output));
			return;
		}
		for (int i = start ; i < a.length ; i++) {
			output[depth] = a[i];
			combination(depth+1, i+1);
		}
	}
	
	static void permutation(int depth) {
		if(depth == M) {
			System.out.print("permutation"+Arrays.toString(output));
			return;
		}
		
		for (int i = 0 ; i < N ; i++) {
	        if (!visited[i]) {
	            visited[i] = true;
	            output[depth] = a[i];
	            permutation(depth + 1);
	            visited[i] = false;
	        }
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		a = new int[N];
		output = new int[M];
		visited = new boolean[N];
		for (int i = 0 ; i < N ; i++) {
			a[i] = i+1;
		}
		
		combination(0,0);
		output = new int[M];
		permutation(0);
	}

}
