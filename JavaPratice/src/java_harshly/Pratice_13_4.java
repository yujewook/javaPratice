package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_4 {
/*
 *  ��� ���� ���� ���ϱ�
 *  1. ��ü �� for�� 
 *  2. ���� �� �����ֱ�
 *  3. ���� ���� ������ �� ���� �ϱ� 
 */
	public static void main(String[] args) {

		int arrayInt[][] = { {10,20,30,0}
						    , {40,50,60,0}
						    , {0,0,0,0}};
		int sum = 0;
		int colSum = 0;
		//������
		for(int i = 0 ; i < arrayInt[i].length -2 ; i++ ) {
			sum = 0 ; //�� �� ���� �ʱ�ȭ
			for(int j = 0 ; j < arrayInt[i].length -1 ; j++ ) { 
				//���� ��ü�� ���ϱ� 
				sum += arrayInt[i][j];
				arrayInt[i][arrayInt[i].length-1] = sum;
			}
		}
		//���� �� 
		for(int i = 0 ; i < arrayInt.length+1 ; i++ ) {
			colSum = 0;
			for(int j = 0 ; j < arrayInt.length-1 ; j++ ) {
				colSum += arrayInt[j][i];
				arrayInt[2][i] = colSum; 
			}
			
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}