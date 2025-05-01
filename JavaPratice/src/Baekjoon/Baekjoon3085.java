package Baekjoon;

import java.util.Scanner;

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
		output = board.clone();
		char temp = output[row][col+1];
		output[row][col+1] = output[row+1][col];
		output[row+1][col] = temp;
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
			for(int j = 0 ; j < board.length ; j++) {
				
			}
		}
	

		return max; 
	}
}


