package java_harshly;

import java.util.Scanner;

public class Pratice_14_1 {
	/*
	 * 1. 입력값 받기
	 * 2. 변수 세개를 사용입력 받는 메소드
	 * 3. 변수 세개 에서 최대값 찾는 매소드 
	 */
		public static void main(String[] args) {
			int output[] = makeArray();
			int max = getMax(output);
			System.out.println(max);
		}
		
		public static int[] makeArray() {
			Scanner sc = new Scanner(System.in);
			int output[] = new int [3];
			System.out.println("정수 세개 입력");
			for(int i = 0 ; i < output.length ; i++) {
				output[i] = sc.nextInt();
			}
			sc.close();
			return output;
		}
		
		public static int getMax(int[]output) {
			int max = 0;
			for(int i = 0 ; i < output.length ; i++) {
				if(max < output[i]) {
					max = output[i];
				}
			}
			return max;
		}
}
