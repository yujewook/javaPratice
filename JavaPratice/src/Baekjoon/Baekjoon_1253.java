package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1253 {

	public static void main(String[] args) throws Exception {
		/************************************************************************
		 *�����Ҵ� 
		 ***********************************************************************/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		int arrayN [] = new int[N];
		/************************************************************************
		 *������ �ֱ�
		 ***********************************************************************/
		for(int i = 0 ; i < N ;i++) {
			arrayN[i]= Integer.parseInt(stringTokenizer.nextToken());
		}

		/************************************************************************
		 *������ ��
		 ***********************************************************************/
		int count = 0;
		for(int i = 0 ; i < N-1 ;i++) {
			int a = arrayN[i]; 
			
			for(int j = 0 ; j < N ; j++) {
				a = a + arrayN[j];
				
			}
				
			
		}

		System.out.println(count);
	}

}
