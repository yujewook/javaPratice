package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//구간합 구하기
public class Baekjoon_11659_1 {
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer strToken = new StringTokenizer(br.readLine());
		/**************************************************************************
		 * 변수
		 ***************************************************************************/
		int suNo = Integer.parseInt(strToken.nextToken()); // 데이터개수
		int quizNo = Integer.parseInt(strToken.nextToken()); // 질문개수
		long arraySum[] = new long[suNo+1]; // 0번째 인덱스는 0
		
		/**************************************************************************
		 * 합배열
		 * 5 4 3 2 1 입력시 
		 * [0, 5, 9, 12, 14, 15] 이 나와야 한다.
		 ***************************************************************************/
		strToken = new StringTokenizer(br.readLine());//입력값 초기화
		for (int i = 1 ; i <= suNo ; i++) {
			arraySum[i] = arraySum[i-1] + Integer.parseInt(strToken.nextToken());
		}
		System.out.println(Arrays.toString(arraySum)); // 0
		/**************************************************************************
		 * 시작점 끝점
		 ***************************************************************************/
		for (int i = 0 ; i < quizNo ; i++) {
			strToken = new StringTokenizer(br.readLine());//입력값 초기화
			int first = Integer.parseInt(strToken.nextToken());
			int last = Integer.parseInt(strToken.nextToken());
			System.out.println( arraySum[last]- arraySum[first-1]);
		}
		
		
		
	}
}
