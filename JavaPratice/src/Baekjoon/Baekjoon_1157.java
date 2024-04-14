package Baekjoon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Baekjoon_1157 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String result = sc.nextLine();
		sc.close();
		
		char array[] = new char[result.length()];
		
		for (int i = 0 ; i < result.length() ;i++) {
            char currentChar = result.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                array[i] = Character.toLowerCase(currentChar);
            } else {
                array[i] = result.charAt(i);
            }
		}
		
		HashMap<Character, Integer> mapValue = new HashMap<>();
		
		for (int i = 0 ; i < result.length() ;i++) {
			int count = 0;
			for(int j = 0 ; j < array.length ; j++) {
				if(array[i] == array[j]) {
					++count;
				}
			}//비교for
			mapValue.put(array[i], count);
		} //for 끝
		
		int maxValue = Integer.MIN_VALUE; // 최소값으로 초기화
		char maxKey1 = '\0'; // 초기화된 문자
		char maxKey2 = '\0'; // 초기화된 문자

		for (Map.Entry<Character, Integer> entry : mapValue.entrySet()) {
		    int value = entry.getValue();
		    if (value > maxValue) {
		        maxValue = value;
		        maxKey2 = maxKey1; // 기존의 최대값을 두 번째 최대값으로 이동
		        maxKey1 = entry.getKey(); // 새로운 최대값 설정
		    } else if (value == maxValue) {
		        maxKey2 = '?'; // 최대값이 같으면 두 번째 최대값을 '?'로 설정
		    }
		}
		if(maxKey2 == '?') {
			System.out.print(maxKey2);
		} else {
			if (Character.isLowerCase(maxKey1)) {
				maxKey1 = Character.toUpperCase(maxKey1);
            }
            System.out.print(maxKey1);
		}
		
		
		
		
		
	}

}/*import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
 
		int[] arr = new int[26]; // 영문자의 개수는 26개임
		String s = in.next();
 
		for (int i = 0; i < s.length(); i++){
 
			if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z') { // 대문자 범위
				arr[s.charAt(i) - 'A']++;	// 해당 인덱스의 값 1 증가
			}
    
			else {	// 소문자 범위
				arr[s.charAt(i) - 'a']++;
			}
		}
 
 
		int max = -1;
		char ch = '?';
 
		for (int i = 0; i < 26; i++) {
			if (arr[i] > max) {
				max = arr[i];
				ch = (char) (i + 65); // 대문자로 출력해야하므로 65를 더해준다.
			}
			else if (arr[i] == max) {
				ch = '?';
			}
		}
 
		System.out.print(ch);
	}
 
}*/
