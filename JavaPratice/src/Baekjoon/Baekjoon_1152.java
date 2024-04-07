package Baekjoon;

import java.util.Scanner;

public class Baekjoon_1152 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		input=input.trim();
		int count = 0;
		
		String[] words = input.split("\\s+");
		count = words.length; 
		System.out.println(count);
	}
}
/*import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		System.out.print(st.countTokens());
	}
}*/