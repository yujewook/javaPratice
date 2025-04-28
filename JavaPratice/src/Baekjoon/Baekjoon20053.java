package Baekjoon;

import java.util.Scanner;

/*
 * ����: ���� 20053�� - �ּ�, �ִ� 2
 * ��ũ: https://www.acmicpc.net/problem/20053
 *
 * ���� ���:
 * - ���� ���� �׽�Ʈ ���̽��� �־�����.
 * - �� �׽�Ʈ ���̽����� �Է¹��� �������� �ּڰ��� �ִ��� ����Ѵ�.
 * 
 * �Է�:
 * - ù° �ٿ� �׽�Ʈ ���̽� ���� T�� �־�����. (1 �� T �� 1,000)
 * - �� �׽�Ʈ ���̽�����:
 *     - ù ��: ���� N (1 �� N �� 1,000)
 *     - ��° ��: N���� ���� (���� -1,000,000 �̻� 1,000,000 ����)
 * 
 * ���:
 * - �� �׽�Ʈ ���̽����� �ּڰ��� �ִ��� �� �ٿ� ���
 * 
 * ���� �Է�:
 * 2
 * 5
 * 20 10 35 30 7
 * 3
 * -10 0 10
 * 
 * ���� ���:
 * 7 35
 * -10 10
 */
public class Baekjoon20053 {

    public static void main(String[] args) {
        // ���⿡ �ڵ� �ۼ��ϸ� ��
    	Scanner sc = new Scanner(System.in);
    	int testCase = sc.nextInt();
    	//�׽�Ʈ ���̽� ������ ���� for��
    	for(int i = 0 ; i < testCase ; i++) {
    		//�迭�� 
    		int n = sc.nextInt();
    		int[] arrayInt = new int[n];
    		for( int j = 0 ; j < arrayInt.length ; j++ ) {
    			arrayInt[j] = sc.nextInt(); 
    		}
    		int max = -1000001;
    		int min = 1000001;
    		for( int j = 0 ; j < arrayInt.length ; j++ ) {
    			if ( min > arrayInt[j]) {
    				min = arrayInt[j];
    			}
    			if ( max < arrayInt[j] ) {
    				max = arrayInt[j];
    			}
    		}
    		System.out.println(min + " " + max);
    	}
    	
    }

}
