package java_harshly_part2;

import java.util.Scanner;

/* 1. Mystring 클래스
 * 2. 동적 할당된 byte[]에 문자열 저장
 * 3.
 */
public class MyString_pratice_01 {
	private byte[] string;
	
	public void setString(byte[] string) {
		this.string = string;
	}
	//문자열로 변환하는 것 
	public String getString(byte[] param) {
		char toChar[] = new char[param.length];
		for(int i = 0 ; i < param.length ; i++) {
			toChar[i] = (char) param[i];
		}
		return String.copyValueOf(toChar);
	}

	int length (byte[] string) {
		return string.length;
	}
	int intcompareTo(byte[] anotherinput) {
		byte v1[] = this.string;
		byte v2[] = anotherinput;
		int result = 0;
		if (v1.length < v2.length) {
			result = -1;
		} else if (v1.length == v2.length) {
			result = 0;
		} else {
			result = 1;
		}
		return result; 
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputString = sc.nextLine();
		
		MyString_pratice_01 mp = new MyString_pratice_01();
		byte[] string = inputString.getBytes();
		mp.setString(string);
		System.out.println("값" + mp.getString(mp.string));
		int length = mp.length(mp.string);
		System.out.println("길이"+length);
		System.out.println("==================================비교 시작==================================");
		inputString = sc.nextLine();
		byte[] compareString = inputString.getBytes();
		int result = mp.intcompareTo(compareString);
		System.out.println("비교 : " + result);

	}

}
