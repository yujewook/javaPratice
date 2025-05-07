package Baekjoon;

import java.util.Scanner;
/**
 * 문제: 백준 3085번 - 사탕 게임
 * https://www.acmicpc.net/problem/3085
 *
 * 문제 설명:
 * - N x N 보드에 다양한 색의 사탕이 배치되어 있음
 * - 인접한 두 칸의 사탕을 서로 교환하여, 
 *   가장 긴 같은 색 사탕의 연속 개수를 찾는 문제 (가로 또는 세로 가능)
 * - 교환은 한 번만 가능하며, 최댓값만 구하면 됨
 *
 * 입력:
 * - 첫 줄: 보드 크기 N (3 ≤ N ≤ 50)
 * - 다음 N줄: 사탕 색상 (한 줄당 N개의 문자, 예: 'C', 'P', 'Z', 'Y')
 *
 * 출력:
 * - 가장 긴 연속 부분의 사탕 개수 출력
 *
 * 예제 입력:
 * 5
 * YCPZY
 * CYZZP
 * CCPPP
 * YCYZC
 * CPPZZ
 *
 * 예제 출력:
 * 4
 *
 * 풀이 아이디어:
 * - 모든 인접한 사탕 쌍을 한번씩 교환해 보고
 * - 매번 전체 보드에서 최대 연속 개수를 계산
 * - 다시 원래대로 복구하고 다음 스왑으로 진행
 * - 최대값을 갱신해가며 최종 정답 출력
 */
public class Baekjoon3085 {
	
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	char[][] board = {
    		    {'Y', 'C', 'P', 'Z', 'Y'},
    		    {'C', 'Y', 'Z', 'Z', 'P'},
    		    {'C', 'C', 'P', 'P', 'P'},
    		    {'Y', 'C', 'Y', 'Z', 'C'},
    		    {'C', 'P', 'P', 'Z', 'Z'}
    		};
    	char[][] temp = new char[board.length][board.length]; 
    	for(int i = 0 ; i < board.length ; i++) {
    		for(int j = 0 ; j < board.length ; j++) {
    			temp = swap(board, i, j);
    			count(temp, i, j);
        	}
    	}

    }
    	
	static char[][] swap( char[][] board , int row , int col) {
		char[][] output = new char[board.length][board.length]; 
		//마지막행 이면 밑에 행에서 교체 안함
		if(row == board.length-1) {
			return board;
		}
		//마지막열 이면 교체 안함
		if(col == board.length-1) {
			return board;
		}
		for (int i = 0; i < board.length; i++) {
		    output[i] = board[i].clone();
		}

		// 아래쪽 교환
		if (row + 1 < board.length) {
		    char temp = output[row][col];
		    output[row][col] = output[row + 1][col];
		    output[row + 1][col] = temp;
		}
		return output;
	}
	// 전체 보드에서 가로/세로 줄 중 가장 긴 사탕 개수를 계산	
	static int count(char[][] board , int row , int col) {
		int max = 0;
		//좌로세기
		int x = 0;
		//우로세기
		int y = 0;

		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board[i].length ; j++) {
				char temp = board[i][j];
			}
		}
	

		return max; 
	}
}


