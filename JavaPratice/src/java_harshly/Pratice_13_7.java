package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_7 {
/*
 * ������ ���
 * 1   2   3   4   5
 * 16  17  18  19  6
   15  24  25  20  7
   14  23  22  21  8
   13  12  11  10  9
 */
/* 1. ���� �������� �ϴ� for ��
 * 2. �������� ���� for��
 */	
	public static void main(String[] args) {

		int arrayInt[][] = new int [5][5];
		int cnt = 1;
		int rowEndIndex = arrayInt.length;
		int rowCnt = 0; 
		int colEndIndex = arrayInt.length;
		int colCnt = 0;
		
		for( int i = 0 ; i < arrayInt.length ; i++ ) { //��
			//�¿��� ��
			//���� ������ ���� ��ĭ�����̵� �ϱ� ������ i���� ����
			for(int j = i ; j < rowEndIndex ;j++) {
				arrayInt[i][j] = cnt++;
			}
			colEndIndex--;
			rowCnt++;
			//������ �Ʒ�
			for(int j = i ; j <= colEndIndex ;j++) {
				arrayInt[j][colEndIndex] = cnt++;
			}
			rowEndIndex--;
			//�¿��� ��
			/*
			for(int j = 0 ; j >= colCnt ;j++) {
				arrayInt[j][colEndIndex] = cnt++;
			} 
			colCnt++;
*/
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}