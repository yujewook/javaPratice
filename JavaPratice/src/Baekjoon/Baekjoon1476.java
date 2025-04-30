package Baekjoon;

import java.util.Scanner;

/**
 * ����: ���� 1476�� - ��¥ ���
 * ��ũ: https://www.acmicpc.net/problem/1476
 * 
 * ���� ���:
 * - ����(E), �¾�(S), ��(M)�� ������ ���� 15, 28, 19���� �ֱ�� �ݺ��ȴ�.
 * - �Է����� E, S, M�� �־��� ��, �� �ֱⰡ ��� ��ġ�ϴ� ���� ���� ������ ���϶�.
 * - ������ 1����� �����ϸ�, ������ ���� �ִ밪�� ������ 1�� ��ȯ�ȴ�.
 * 
 * �Է� ����:
 * 1 16 16
 * 
 * ��� ����:
 * 5266
 * 
 * Ǯ�� ����:
 * - year�� 1���� �ϳ��� ������Ű�� �� ���� ��� ��ġ�� ������ �ݺ�
 * - ������ ���� (�� - 1) % �ֱ� + 1 �� ����
 * - �ּҰ������ 15 * 28 * 19 = 7980�� �ѱ��� �ʾƵ� �ذ� ����
 */
public class Baekjoon1476 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int E = sc.nextInt(); // ���� ���ۿ��� (1 ~ 15)
        int S = sc.nextInt(); // �¾� ���� ���� (1 ~ 28)
        int M = sc.nextInt(); // �� ���� ���� (1 ~ 19)
        
        int year = 1;
        
        while(true) {
        	if( ((year-1)%15) +1 == E 
        	   && ((year-1)%28) +1 == S
        	   && ((year-1)%19) +1 == M) {
        		break;
        	}
        	year++;
        }
        System.out.println("E = " + E);
        System.out.println("S = " + S);
        System.out.println("M = " + M);
        System.out.println("YEAR "+year);

    
    }
}
