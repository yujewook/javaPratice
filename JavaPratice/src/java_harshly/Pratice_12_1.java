package java_harshly;

import java.util.Scanner;

public class Pratice_12_1 {
/*���հ���
 * 1. while ������ 1~10
 * 2. for ������ 1~10
 * 3. ���մ��� �ϴ� ���� Ȱ�� 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int cnt = 0;
		
		while(cnt < 10) {
			sum += ++cnt;
		}
		System.out.println("while total:"+sum);
		
		sum = 0;
		for(int i = 1 ; i < 11 ; i++) {
			sum += i;
		}
		System.out.println("for total:"+sum);
	}

}
