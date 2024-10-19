package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1940 {
	
	public static void main(String args[]) throws Exception {
		/*************************************************************************
		 *����
		 ************************************************************************/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int a[] = new int [N]; 
		StringTokenizer strToken = new StringTokenizer(br.readLine());
		/*************************************************************************
		 *�迭�� ������ �ֱ�
		 ************************************************************************/		
		for( int i = 0 ; i < N ; i++) {
			a[i] = Integer.parseInt(strToken.nextToken());
		}
		Arrays.sort(a);
		/*************************************************************************
		 *������Ʈ ����
		 ************************************************************************/
		int index_first = 0 ;
		int index_last = N-1 ;
		int count = 0;
		/*************************************************************************
		 *������Ʈ_����
		 ************************************************************************/
		while(index_first < index_last) {
			if( a[index_first] + a[index_last] > M ) {
				index_last--;
			} else if ( a[index_first] + a[index_last] < M ) {
				index_first ++;
			} else {
				count++;
				index_first ++;
				index_last--;
			}
		}
		
		System.out.println(count);
		br.close();		
	}
}
