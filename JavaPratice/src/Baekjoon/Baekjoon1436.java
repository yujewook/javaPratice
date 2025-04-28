package Baekjoon;

import java.util.Scanner;

/*
문제: https://www.acmicpc.net/problem/7568
문제 제목: 백준 7568번 - 덩치

문제 요약:
몸무게와 키를 입력받아, 자신보다 덩치가 큰 사람이 몇 명인지 세고,
등수를 출력하는 문제. 몸무게와 키 모두 작아야 덩치가 작다고 판단한다.
.


예제 입력:
5
55 185
58 183
88 186
60 175
46 155

예제 출력:
 2 2 1 2 5
*/
public class Baekjoon1436 {
	
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int[][] people = {
			    		    {55, 185},
			    		    {58, 183},
			    		    {88, 186},
			    		    {60, 175},
			    		    {46, 155} };
    	
    	for(int i = 0 ; i < people.length ; i++ ) {
    		int rank = 1;
    		for(int j = 0 ; j < people.length ; j++ ) {
    			if( i == j ) {
    				continue;
    			}
    			if(people[i][0] < people[j][0] && people[i][1] < people[j][1]) {
    				rank++;
    			}
    				
    		}
    		System.out.print(rank + " ");
    	}
    	
	}
	
}


