package Baekjoon;

import java.sql.Array;
import java.util.HashSet;
import java.util.Scanner;

public class Baekjoon_2562 {

	public static void main(String[] args) {
	/*	Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		HashSet<Integer> a = new HashSet<Integer>();
		
		for(int c = 0 ; c < N ; c++ ) {
			a.add(sc.nextInt());
		}
		
		Object[] c = a.toArray();
		int max = 0;
		int spot = 0;
		
		for(int d = 0 ; d < c.length ; d++ ) {
			if ( (int) c[d] > max ) {
				max = (int) c[d];
				spot++;
			}
		}
		
		System.out.println(max + " " + spot);
		*/
		
		
        Scanner sc = new Scanner(System.in);

        int arr[] = new int[9];
        int max = 0;
        int count = 0;

        for(int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();

            if(arr[i] > max) {					// (조건) max 보다 arr[i] 값이 더 클경우
                max = arr[i];					// max 변수에 arr[i] 값을 넣어줌
                count = i+1;					
            }
        }
        System.out.println(max);
        System.out.println(count);
    }

}
