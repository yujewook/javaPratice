package java_harshly;

import java.util.Scanner;

public class Pratice_14_1 {
	/*
	 * 1. �Է°� �ޱ�
	 * 2. ���� ������ ����Է� �޴� �޼ҵ�
	 * 3. ���� ���� ���� �ִ밪 ã�� �żҵ� 
	 */
		public static void main(String[] args) {
			int output[] = makeArray();
			int max = getMax(output);
			System.out.println(max);
		}
		
		public static int[] makeArray() {
			Scanner sc = new Scanner(System.in);
			int output[] = new int [3];
			System.out.println("���� ���� �Է�");
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
