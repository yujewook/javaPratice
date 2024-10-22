package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Baekjoon_1253_1 {

	public static void main(String[] args) throws Exception {
		/************************************************************************
		 *변수할당 
		 ***********************************************************************/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
		long arrayN [] = new long [N];
		/************************************************************************
		 *데이터 넣기
		 ***********************************************************************/
		for(int i = 0 ; i < N ;i++) {
			arrayN[i] = Long.parseLong(stringTokenizer.nextToken());
		}
		Arrays.sort(arrayN);
		/************************************************************************
		 *데이터 비교
		 ***********************************************************************/
		int result = 0;
		for(int k = 0 ; k < N ; k++) {
			long find = arrayN[k];
			int i = 0 ;
			int j = N-1;
			
			while(i < j) {
				if (arrayN[i] + arrayN[j] == find) {
					if (i != k && j != k ) {
						result++;
						break;
					} else if ( i == k ) {
						i++;
					} else if ( i == k ) {
						j--;
					}
				} else if(arrayN[i] + arrayN[j] < find) {
					i++;
				} else {
					j--;
				}
			}
		}

		System.out.println("갯수"+ result);
		br.close();
	}

}
