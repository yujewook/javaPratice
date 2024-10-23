package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_12891 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer strToken = new StringTokenizer(bf.readLine());
		int S = Integer.parseInt(strToken.nextToken()); //전체 문자열 길이
		int P = Integer.parseInt(strToken.nextToken());
		
		int Result = 0;
		int checkArr [] = new int[4];//비밀번호 조건
		int myArr [] = new int[4]; //현재 상태 배열

		char A[] = new char[S];
		int chechSecret = 0; //비밀번호 조건이 맞는경우 
		
		A = bf.readLine().toCharArray();
		strToken = new StringTokenizer(bf.readLine());//새로운 문자배열
		for (int i = 0 ; i < 4 ; i++) {
			checkArr[i] = Integer.parseInt(strToken.nextToken());
			
			if( checkArr[i] == 0 ) {
				chechSecret++;
			}
		}
		
	}

}
