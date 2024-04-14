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
			}//��for
			mapValue.put(array[i], count);
		} //for ��
		
		int maxValue = Integer.MIN_VALUE; // �ּҰ����� �ʱ�ȭ
		char maxKey1 = '\0'; // �ʱ�ȭ�� ����
		char maxKey2 = '\0'; // �ʱ�ȭ�� ����

		for (Map.Entry<Character, Integer> entry : mapValue.entrySet()) {
		    int value = entry.getValue();
		    if (value > maxValue) {
		        maxValue = value;
		        maxKey2 = maxKey1; // ������ �ִ밪�� �� ��° �ִ밪���� �̵�
		        maxKey1 = entry.getKey(); // ���ο� �ִ밪 ����
		    } else if (value == maxValue) {
		        maxKey2 = '?'; // �ִ밪�� ������ �� ��° �ִ밪�� '?'�� ����
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
 
		int[] arr = new int[26]; // �������� ������ 26����
		String s = in.next();
 
		for (int i = 0; i < s.length(); i++){
 
			if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z') { // �빮�� ����
				arr[s.charAt(i) - 'A']++;	// �ش� �ε����� �� 1 ����
			}
    
			else {	// �ҹ��� ����
				arr[s.charAt(i) - 'a']++;
			}
		}
 
 
		int max = -1;
		char ch = '?';
 
		for (int i = 0; i < 26; i++) {
			if (arr[i] > max) {
				max = arr[i];
				ch = (char) (i + 65); // �빮�ڷ� ����ؾ��ϹǷ� 65�� �����ش�.
			}
			else if (arr[i] == max) {
				ch = '?';
			}
		}
 
		System.out.print(ch);
	}
 
}*/
