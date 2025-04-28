package Baekjoon;

import java.util.Scanner;

/*
 * ����: ���� 10818�� - �ּ�, �ִ�
 * ��ũ: https://www.acmicpc.net/problem/10818
 *
 * ���� ���:
 * - N���� ������ �Է¹޾� �ּڰ��� �ִ��� ����ϴ� ����.
 * - ���� ���� �ݺ����� ����Ͽ� ���� �ּڰ�, �ִ��� ã�ƾ� �Ѵ�.
 * 
 * �Է�:
 * - ù° ��: ���� N (1 �� N �� 1,000,000)
 * - ��° ��: N���� ���� (���� -1,000,000 �̻� 1,000,000 ����)
 * 
 * ���:
 * - �ּڰ��� �ִ��� �������� �����Ͽ� ���
 * 
 * ����:
 * �Է�: 
 * 5
 * 20 10 35 30 7
 * 
 * ���:
 * 7 35
 */
public class Baekjoon10818 {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int min = 1000001;
    	int max = -1000001;
    	//�迭
    	int[] arrayInput = new int[N];
    	
    	for( int i = 0 ; i < arrayInput.length ; i++ ) {
    		arrayInput[i] = sc.nextInt();
    		//����� �Է� �޴� ���ÿ� �ҵ� �Է��� �ް� �ٷ� ���� ���� �� �ϰ� 
    		if(i != 0 ) {
    			// Ȥ�ó� �������� ���� max < arrayInput[i] �� ���� ture max < arrayInput[i] ������� 
    			if ( max < arrayInput[i] ) {
    				max = arrayInput[i];
    			}
    			if ( min > arrayInput[i] ) {
    				min = arrayInput[i];
    			}
    		}
    	}
    	System.out.print(min+" "+max);//������ �̰� ���ϴ� �˰����� �ƴҵ� �ʹ�.
    	//�ʱ�ȭ �κ�
    	min = 1000001;
    	max = 0;
    	for( int i = 0 ; i < arrayInput.length ; i++ ) {
    		if ( max < arrayInput[i] ) {
				max = arrayInput[i];
			}
			if ( min > arrayInput[i] ) {
				min = arrayInput[i];
			}
    	}
    	System.out.print("\t");
    	System.out.print(min+" "+max);//������ �̰� ���ϴ� �˰����� �ƴҵ� �ʹ�.
    }

}
