package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_6 {
/*
 * �簢�� ���� ���� �������� ����ä���ֱ�
 * 1   2   3   4   5
 * 10  9   8   7   6
   11  12  13  14  15
   20  19  18  17  16
   21  22  23  24  25
 */
	public static void main(String[] args) {

		int arrayInt[][] = new int [5][5];
		int cnt = 1;
		
		for( int i = 0 ; i < arrayInt.length ; i++ ) { //��
			//�� �б� ó��
			if (i % 2 != 0 ) {
				for (int j = arrayInt.length-1 ; j >= 0 ; j-- ) {
					arrayInt[i][j] = cnt++;
				}
			} else {
				for (int j = 0 ; j < arrayInt.length ; j++ ) {
					arrayInt[i][j] = cnt++;
				}
			}
			
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}