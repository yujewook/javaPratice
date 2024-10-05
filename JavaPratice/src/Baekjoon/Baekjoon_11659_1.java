package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//������ ���ϱ�
public class Baekjoon_11659_1 {
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer strToken = new StringTokenizer(br.readLine());
		/**************************************************************************
		 * ����
		 ***************************************************************************/
		int suNo = Integer.parseInt(strToken.nextToken()); // �����Ͱ���
		int quizNo = Integer.parseInt(strToken.nextToken()); // ��������
		long arraySum[] = new long[suNo+1]; // 0��° �ε����� 0
		
		/**************************************************************************
		 * �չ迭
		 * 5 4 3 2 1 �Է½� 
		 * [0, 5, 9, 12, 14, 15] �� ���;� �Ѵ�.
		 ***************************************************************************/
		strToken = new StringTokenizer(br.readLine());//�Է°� �ʱ�ȭ
		for (int i = 1 ; i <= suNo ; i++) {
			arraySum[i] = arraySum[i-1] + Integer.parseInt(strToken.nextToken());
		}
		System.out.println(Arrays.toString(arraySum)); // 0
		/**************************************************************************
		 * ������ ����
		 ***************************************************************************/
		for (int i = 0 ; i < quizNo ; i++) {
			strToken = new StringTokenizer(br.readLine());//�Է°� �ʱ�ȭ
			int first = Integer.parseInt(strToken.nextToken());
			int last = Integer.parseInt(strToken.nextToken());
			System.out.println( arraySum[last]- arraySum[first-1]);
		}
		
		
		
	}
}
