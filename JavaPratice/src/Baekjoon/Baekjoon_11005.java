package Baekjoon;

import java.util.Scanner;

public class Baekjoon_11005 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int number = sc.nextInt();
		int base = sc.nextInt();
		sc.close();
		
		while(number>0) {
			if(number % base < 10) {
				
			}
		}
    }

}

/*
  import java.io.*;
 
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //10���� ����
        int B = Integer.parseInt(st.nextToken()); //������?

        br.close();

        ArrayList<Character> list = new ArrayList<>();

        while (N > 0) { //���� 0���� Ŭ ���(���̻� B�������� �ٲ� ���� ���� ���)
            if (N % B < 10) { //���� ���������� �������� 10���� ���� ���
                list.add((char) (N % B + '0')); //�׳� �������
            }
            else{ //10���� ũ�ų� ���� ���

                list.add((char) (N % B - 10 + 'A')); 
            }
            N /= B; //B���� ���� => B������ ���ڸ��� ���߱� ����(���⼭ ���ڸ��� ù��° �ڸ��� �ƴ϶�, �׳� ��ĭ�� �ǹ�)
        }

        for (int i = list.size() - 1; i >= 0; i--) { // ��ó������ ����ؾ��ϹǷ�
            System.out.print(list.get(i));
        }
    }
}
*/