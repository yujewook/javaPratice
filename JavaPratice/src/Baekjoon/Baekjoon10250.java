package Baekjoon;

import java.util.Scanner;

/**
 * ����: ���� 10250�� - ACM ȣ��
 * https://www.acmicpc.net/problem/10250
 *
 * ���� ����:
 * - ȣ���� H��, �� ���� W���� ���� ���� (�� ��ȣ�� YYXX ����: YY�� ����, XX�� ȣ��)
 * - �մ��� 1������ ���ʴ�� ���� ����� ������ ���ʴ�� ���� �������� (1�� �� H�� �� 1�� �� ...)
 * - N��° �մԿ��� �����Ǵ� �� ��ȣ�� ���϶�
 *
 * �Է�:
 * - ù ��: �׽�Ʈ ���̽� �� T
 * - ���� T��: �� �ٿ� H W N�� �־���
 *
 * ���:
 * - �� �׽�Ʈ ���̽����� N��° �մ��� �� ��ȣ ��� (YYXX ����, XX�� �׻� 2�ڸ�)
 *
 * ���� �Է�:
 * 2
 * 6 12 10
 * 30 50 72
 *
 * ���� ���:
 * 402
 * 1203
 *
 * Ǯ�� ���̵��:
 * - ���� = N % H (��, �������� 0�̸� H��)
 * - ȣ�� = N / H (��, �������� 0�̸� �״��, �ƴϸ� +1)
 * - ������ ȣ���� YYXX ���·� ��� (ȣ���� �׻� 2�ڸ��� ����ؾ� ��)
 */
public class Baekjoon10250 {
	 public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 int T = sc.nextInt();
		 
		 while(T!=0) {
	         int H = sc.nextInt(); // �� ��
	         int W = sc.nextInt(); // �� �� �� �� (��� �� ��)
	         int N = sc.nextInt(); // N��° �մ�
			 
			 int floor = 0;
			 int room = 0;
			 if ( N%H != 0 ) {
				 room = (N/H)+1;
				 floor = N % H;
			 } else {
				 floor = H;
				 room = N / H;
			 }
			 
			 System.out.printf("%d%02d\n", floor, room);
			 T--;
		 }
	 }
}
