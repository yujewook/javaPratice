package java_harshly;

import java.util.Arrays;
import java.util.Scanner;

public class Pratice_13_5 {
/*
 *  �簢���� ���� ä���
 */
	public static void main(String[] args) {

		int arrayInt[][] = new int [5][5];
		int cnt = 1;
		for( int i = 0 ; i < arrayInt.length ; i++ ) {//���ø���
			for( int j = 0 ; j < arrayInt.length ; j++ ) {//���ø���
				arrayInt[i][j] = cnt++;
			}
		}
		
		for(int[] data : arrayInt) {
			System.out.println(Arrays.toString(data));
		}
		
	}
}