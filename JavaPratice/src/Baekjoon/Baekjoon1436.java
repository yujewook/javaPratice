package Baekjoon;

import java.util.Scanner;

/*
 * 문제: 백준 1436번 - 영화감독 숌
 * 링크: https://www.acmicpc.net/problem/1436
 *
 * 문제 요약:
 * - "666"이 들어가는 수들을 작은 순서대로 나열했을 때
 * - N번째에 오는 수를 출력한다.
 *
 * 입력:
 * - 첫째 줄: 정수 N (1 ≤ N ≤ 10,000)
 *
 * 출력:
 * - N번째 종말의 수를 출력
 *
 * 예시 입력:
 * 2
 *
 * 예시 출력:
 * 1666
 */
public class Baekjoon1436 {

    public static void main(String[] args) {
        // 여기에 코드 작성하면 돼
    	Scanner sc = new Scanner(System.in);
    	int rank = sc.nextInt();
    	sc.close();
    	
    	String num = "666";
    	int flag = 0;
    	int a = 0;
    	for (int N = 1 ; N <= 10000 ; N++) {
 
    		String ex = String.valueOf(N);
    		if (ex.length() >= 3) {
        		if (ex.charAt(ex.length()-1) == num.charAt(num.length()-1) &&
            			ex.charAt(ex.length()-2) == num.charAt(num.length()-2) &&
            			ex.charAt(ex.length()-3) == num.charAt(num.length()-3)) {
            			flag++;
            			if(flag == rank) {
            				System.out.println(ex);
            				break;
            			}
            		};
    		}
    	}
    }

}

/*
 *
 package Baekjoon;

import java.util.Scanner;

public class Baekjoon1436 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rank = sc.nextInt();
        sc.close();
        
        int count = 0;
        int num = 666;
        
        while (true) {
            if (String.valueOf(num).contains("666")) {
                count++;
                if (count == rank) {
                    System.out.println(num);
                    break;
                }
            }
            num++;
        }
    }

}

 */
