package Baekjoon;

import java.util.Scanner;

//������ ���ϱ�
public class baekjoon_11659 {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		/***********************************************************************
		 * ���� 
		 ***********************************************************************/
		System.out.print("�Է� ���� ������ ����");
		int dataNum = sc.nextInt();
		System.out.print("���� ����");
		int range = sc.nextInt();
		int arrData [] = new int[dataNum];
		
		/***********************************************************************
		 *�迭�� �ֱ� ���ڳֱ�
		 ***********************************************************************/
		for ( int i = 0 ; i < arrData.length ; i++ ) {
			System.out.print( (i+1) + "��° �� ");
			arrData[i] = sc.nextInt();
		}

		/***********************************************************************
		 *�迭�� �� ���ϱ�
		 ***********************************************************************/
		int arrDataSum [] = new int[dataNum+1];
		for ( int i = 1 ; i < arrDataSum.length ; i++ ) {
			arrDataSum [i] = arrDataSum [i-1] + arrData[i-1];
		}
		
		/***********************************************************************
		 *������ �� ���ϱ�
		 ***********************************************************************/
		while(range != 0) {
			System.out.println("���� ����");
			int i = sc.nextInt();
			System.out.println("���� ��");
			int j = sc.nextInt();
			System.out.println(arrDataSum[j] - arrDataSum[i-1]);
			range--;
		}
		
	}
}
