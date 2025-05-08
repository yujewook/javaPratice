package Baekjoon;
//���� 1010�� - �ٸ� ����
//���� ����:
//���ʿ� N���� ����Ʈ, ���ʿ� M���� ����Ʈ�� ���� ��,
//���� ����Ʈ N���� ��� ���� �ٸ� ���� ����Ʈ�� �����ϴ� ����� ���� ���ϴ� ����
//����: �� ����Ʈ���� �ִ� �� ���� �ٸ��� ������ �� �ִ� (�ߺ� �Ұ�, ���� ��� ����)

//�Է� ����:
//3           �� �׽�Ʈ ���̽� ��
//2 2         �� N=2, M=2 �� ���: 1
//1 5         �� N=1, M=5 �� ���: 5
//13 29       �� N=13, M=29 �� ���: 67863915

import java.util.Scanner;

public class Baekjoon1010 {
 static int dep = 0;
 static int count = 0;
 // ���� �Լ�: C(m, n)
 static int combination(int m, int n) {
     int result = 1;

     // ����ȭ: n > m-n�� ���, ��Ī�� �̿�
     if (n > m - n) {
         n = m - n;
     }

     for (int i = 1; i <= n; i++) {
         result *= (m - i + 1);
         result /= i;
     }

     return result;
 }
 
 public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);
	 int t = sc.nextInt();
	 int n , m ;
	 for (int i = 0 ; i < t ; i++) {
		 n = sc.nextInt();
		 m = sc.nextInt();
		 System.out.println(combination(n,m));
	 }
	 

 }
 
}
