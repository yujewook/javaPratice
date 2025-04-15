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
    	//최대 값 찾기 for문 이중이 아닌이상 logn은 같다고 알고 있음
    	double max = 0.0;
    	for (int i = 0; i < n; i++) {
    	   if( max < scores[i]) {
    		   max = scores[i];
    	   }
    	}
    	double sum = 0;
    	//점수 바꾸기와 최종 합계 구하기
    	for (int i = 0; i < n; i++) {
    	   scores[i] = (scores[i]/max)*100;
    	   sum += scores[i];
    	}
    	
    	System.out.println(sum/n);
	}
}


