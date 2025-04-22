package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 📌 문제: 백준 19532번 - 수학은 비대면강의입니다
 * 🔗 링크: https://www.acmicpc.net/problem/19532
 *
 * 🎯 문제 설명:
 * 두 정수 x, y에 대해 다음과 같은 두 개의 일차방정식이 주어진다.
 *
 *   a * x + b * y = c  
 *   d * x + e * y = f
 *
 * 이 식을 만족하는 정수 x와 y를 구하는 프로그램을 작성하라.
 * 단, 정답이 되는 x와 y는 -999 이상 999 이하의 정수 범위 내에 존재하며,
 * 문제 조건상 해가 유일하게 존재함이 보장된다.
 *
 * 🧾 입력 형식:
 * a b c
 * d e f
 * - 각각 정수이며, 공백으로 구분
 *
 * 🧾 출력 형식:
 * x y
 * - 조건을 만족하는 정수 해를 출력
 *
 * 🧪 입력 예시:
 * 1 3 -1
 * 4 1 7
 *
 * ✅ 출력 예시:
 * 2 -1
 *
 * 🧠 제한 조건:
 * -999 <= x, y <= 999
 * 해는 유일하게 존재함
 *
 */

class Baekjoon19532 {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	//카드갯수
    	int a = sc.nextInt();
    	int b = sc.nextInt();
    	int c = sc.nextInt();
    	int d = sc.nextInt();
    	int e = sc.nextInt();
    	int f = sc.nextInt();

    	for(int x = -999 ; x < 1000 ; x++) {
    		for(int y = -999 ; y < 1000 ; y++) {
        		if(c == a*x +b*y && f == d*x +e*y ) {
        			System.out.println(x + " " + y);
        		}
        	}
    	}
    	
    	
	}
}


