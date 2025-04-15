package Baekjoon;

import java.util.Scanner;
//input
//3
//40 80 60
//output
//75.0

class Baekjoon1546 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	double[] scores = new double[n];

    	for (int i = 0; i < n; i++) {
    	    scores[i] = sc.nextInt();
    	}
    	sc.close();
    	//�ִ� �� ã�� for�� ������ �ƴ��̻� logn�� ���ٰ� �˰� ����
    	double max = 0.0;
    	for (int i = 0; i < n; i++) {
    	   if( max < scores[i]) {
    		   max = scores[i];
    	   }
    	}
    	double sum = 0;
    	//���� �ٲٱ�� ���� �հ� ���ϱ�
    	for (int i = 0; i < n; i++) {
    	   scores[i] = (scores[i]/max)*100;
    	   sum += scores[i];
    	}
    	
    	System.out.println(sum/n);
	}
}


