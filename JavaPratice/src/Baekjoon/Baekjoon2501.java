package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 백준 2501번 - 약수 구하기
 * 어떤 자연수 𝑁이 있을 때, 그 수의 약수들 중에서 K번째로 작은 수를 출력하는 프로그램을 
 * input 6 3
 * ouput 3
 */

class Baekjoon2501 {
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    	List<Integer> output = new ArrayList<Integer>();
    	for(int i = 1; i <=N ; i++) {
    		if( N%i == 0 ) {
    			output.add(i);
    		}
    	}
    	int index = output.size() < K ? 0 : K-1 ;
    	System.out.println(index);
    	System.out.println(output.get(index));
	}

	/*내가 푼거 1번
	    Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    	int flag = 0;
    	int result = 0;
    	for(int i = 1 ; i <= N ; i++) {
    		
    		if( N%i == 0 ) {
    			flag++;
    			if(flag == K) {
    				result = i;
    				break;
    			}
    		}
    	}
    	System.out.println(result);
	 */
	
	
}


