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
		for(int i = 0 ; i < N ;i++) {
			int a = arrayN[i]; 
			for(int j = i+1 ; j < N ; j++) {
				int b = a + arrayN[j];
				System.out.println(b);
				for(int z = 0 ; z < N ; z++) {
					if(b == arrayN[z]) {
						count++;
					}
					
				}
			}
				
			
		}

		System.out.println("count "+count);
	}

}
