package Baekjoon;

import java.util.Scanner;

public class Baekjoon_3052 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arr[] = new int [10];
		
		for(int i = 0 ; i < arr.length ; i++) {
			arr[i] = sc.nextInt() % 42;
			
		}
		for(int i = 0 ; i < arr.length ; i++) {
			System.out.print("�迭  :"+arr[i] + " ");
		}
		
		int count = 0;
		
		for(int i = 0 ; i < arr.length ; i++) {
			boolean check = false;
			for(int j = 0 ; j < i ;j++) {
				if (arr[i] == arr[j]) {
					check = true;
					break;
				}else {
					check = false;
				}
				

			}
			if(check == false)
				count++;
			
		}
		System.out.println("����   :: "+count);

	}

}


/*
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		HashSet<Integer> h = new HashSet<Integer>();
		
		for(int i = 0; i<10; i++) {
			h.add(sc.nextInt() % 42);
			// �Է� ���� ���� �������� add �޼ҵ带 ���� HashSet�� ����
		}
		
		sc.close();
		System.out.println(h.size());
		
		
	}

}


import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int[] arr = new int[10];
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt() % 42;

			boolean check = false;
			for (int j = 0; j < i; j++) {
				if (arr[i] == arr[j]) {
					check = true;
					break;
				} else {
					check = false;
				}
			}

			if (check == false) {
				count++;
			}

		}

		System.out.println(count);
	}
}




*/

