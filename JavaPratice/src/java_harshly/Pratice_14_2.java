package java_harshly;

import java.util.Scanner;
 	/*���ڿ��� ������ �޼���
	 * 1. ���ڿ��� ������ �޼���
	 * 2. ����Է� �޴� �޼ҵ�
	 * 3. ���� ���� ���� �ִ밪 ã�� �żҵ� 
	 */
/*
	public static void main(String[] args) {
			String input = makeArray();
			String reverseString = getReverseString(input , 0 , input.length() - 1 );
			System.out.println(reverseString);
		}
		
		public static String makeArray() {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			sc.close();
			return input;
		}
		
	    // ���ڿ��� ��������� ������ �޼���
	    public static String getReverseString(String input, int start, int end) {
	        if (start >= end) {
	            return input; // ���� ����: ���� �ε����� �� �ε������� ũ�ų� ���� ��
	        }

	        // ���ڿ��� ���� �迭�� ��ȯ
	        char[] charArray = input.toCharArray();

	        // start�� end�� ���ڸ� ��ȯ
	        char temp = charArray[start];
	        charArray[start] = charArray[end];
	        charArray[end] = temp;

	        // ��ȯ�� �迭�� ���ڿ��� ��ȯ
	        input = String.valueOf(charArray);

	        // ���� �ܰ�� ��� ȣ��
	        return getReverseString(input, start + 1, end - 1);
	    }
 */
public class Pratice_14_2 {
	/*���ڿ��� ������ �޼���
	 * 1. ���ڿ��� ������ �޼���
	 * 2. ����Է� �޴� �޼ҵ�
	 * 3. ���� ���� ���� �ִ밪 ã�� �żҵ� 
	 */
		public static void main(String[] args) {
			String input = makeArray();
			String reverseString = getReverseString(input , input.length() , 0 );
			System.out.println(reverseString);
		}
		
		public static String makeArray() {
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			sc.close();
			return input;
		}
		
		public static String getReverseString (String input , int length , int start ) {
			int cnt = length-1;
			int startIndex = start;
			if (cnt < 0) {
				return input;
			}

			char[] charArray = input.toCharArray();
			char temp = charArray[startIndex];
			charArray[startIndex] = charArray[cnt];
			charArray[cnt] = temp;
			input = String.copyValueOf(charArray);
			startIndex++;
			cnt--;
			
			String ouput = getReverseString ( input , cnt , startIndex);
			return ouput;
		}
}
