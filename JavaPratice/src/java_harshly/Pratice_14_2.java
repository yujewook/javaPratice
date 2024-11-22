package java_harshly;

import java.util.Scanner;
 	/*문자열을 뒤집는 메서드
	 * 1. 문자열을 뒤집는 메서드
	 * 2. 사용입력 받는 메소드
	 * 3. 변수 세개 에서 최대값 찾는 매소드 
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
		
	    // 문자열을 재귀적으로 뒤집는 메서드
	    public static String getReverseString(String input, int start, int end) {
	        if (start >= end) {
	            return input; // 종료 조건: 시작 인덱스가 끝 인덱스보다 크거나 같을 때
	        }

	        // 문자열을 문자 배열로 변환
	        char[] charArray = input.toCharArray();

	        // start와 end의 문자를 교환
	        char temp = charArray[start];
	        charArray[start] = charArray[end];
	        charArray[end] = temp;

	        // 교환된 배열을 문자열로 변환
	        input = String.valueOf(charArray);

	        // 다음 단계로 재귀 호출
	        return getReverseString(input, start + 1, end - 1);
	    }
 */
public class Pratice_14_2 {
	/*문자열을 뒤집는 메서드
	 * 1. 문자열을 뒤집는 메서드
	 * 2. 사용입력 받는 메소드
	 * 3. 변수 세개 에서 최대값 찾는 매소드 
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
