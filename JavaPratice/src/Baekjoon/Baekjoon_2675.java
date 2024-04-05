package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2675 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("r");
		int r = sc.nextInt();
		//int R[] = new int[r];
		StringBuffer result2 = new StringBuffer();
		for(int i = 0; i < r ; i++ ) {
			System.out.print("R");
			int R = sc.nextInt();
			System.out.print("RESULT");
			String result = sc.next();
			
			
			for(int j = 0 ; j < result.length() ; j++) {
				char a = result.charAt(j);
				String b = String.valueOf(a).repeat(R);
				result2.append(b);
			}
			
			
		}
		System.out.println(result2);
		System.out.println(result2);
	}

}
/*import java.util.Scanner;
public class Main {
 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		for(int i = 0; i < T; i++) {
	
			int R = in.nextInt();
			String S = in.next();	// nextLine() 을 쓰면 error!
			
			for(int j = 0; j < S.length(); j++) {           
				for(int k = 0; k < R; k++) {	// R 만큼 반복 출력
					System.out.print(S.charAt(j));
				}
			}
            
			System.out.println();
		}
	}
}*/
