package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 2798번 - 블랙잭
 *
 * 📝 문제 설명:
 * - 카드의 개수 N (3 ≤ N ≤ 100)과 목표 숫자 M (10 ≤ M ≤ 300,000)이 주어진다.
 * - N장의 카드에는 각각 양의 정수가 쓰여 있음 (중복 없음).
 * - 이 중 3장을 골라, 그 합이 M을 넘지 않으면서 최대한 가까운 값을 구해야 함.
 *
 * 📥 입력:
 * - 첫째 줄: 카드 개수 N, 목표 수 M
 * - 둘째 줄: 카드 N개에 쓰인 수 (공백으로 구분)
 *
 * 📤 출력:
 * - M을 넘지 않으면서 가장 가까운 3장의 카드 합을 출력
 *
 * 📌 예제 입력 1:
 * 5 21
 * 5 6 7 8 9
 *
 * 📌 예제 출력 1:
 * 21
 *
 * 💡 힌트:
 * - 모든 3장의 카드 조합을 다 비교해도 괜찮은 수준 (완전 탐색 가능)
 * - 3중 for문으로 3장씩 골라서 조건 검사하면 됨
 */

class Bacjun2798 {
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	//카드갯수
    	int cardCnt = 5;//sc.nextInt();
    	//총합
    	int totalSum = 21;//sc.nextInt();
    	//카드
    	int[] cardArray = new int[cardCnt];
    	for (int i = 0 ; i < cardCnt ; i++) {
    		cardArray[i] = sc.nextInt();
    	}
    	List<int[]> results = new ArrayList<>();
    	//조합만들기
    	for (int i = 0 ; i < cardArray.length-2 ; i++) {
    		int[] input = new int[3];
    		for(int j = i+1 ; j < cardArray.length-1 ; j++) {
    			for(int z = j+1 ; z < cardArray.length ; z++) {
    				input[0] = cardArray[i];
    				input[1] = cardArray[j];
    				input[2] = cardArray[z];
    			}
    		}
    		results.add(input);
    	}
    	
    	//조합전체읽기
    	for(int i = 0 ; i < results.size() ; i++) {
    		//조합 내부 읽기
    	}

	}
	
	/*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 카드 수
        int M = sc.nextInt();  // 목표 값

        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }

        int maxSum = 0;

        // 완전 탐색: 3중 for문으로 모든 조합 확인
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int sum = cards[i] + cards[j] + cards[k];
                    if (sum <= M && sum > maxSum) {
                        maxSum = sum;
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
    */
}


