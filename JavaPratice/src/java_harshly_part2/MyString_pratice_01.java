package java_harshly_part2;

import java.util.Scanner;

/* 1. Mystring Ŭ����
 * 2. ���� �Ҵ�� byte[]�� ���ڿ� ����
 * 3.
 */
public class MyString_pratice_01 {
	private byte[] string;
	
	public void setString(byte[] string) {
		this.string = string;
	}
	//���ڿ��� ��ȯ�ϴ� �� 
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
		System.out.println("��" + mp.getString(mp.string));
		int length = mp.length(mp.string);
		System.out.println("����"+length);
		System.out.println("==================================�� ����==================================");
		inputString = sc.nextLine();
		byte[] compareString = inputString.getBytes();
		int result = mp.intcompareTo(compareString);
		System.out.println("�� : " + result);

	}

}
