package java_harshly;

import java.util.Scanner;

public class Pratice_10_3 {
/*서바이벌 방식 근데 첫번째 꺼는 토너먼트 방식..
 * 1. 입력값 받기
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int first = sc.nextInt();
		int seconde = sc.nextInt();
		int third = sc.nextInt();
		Boolean a = first > seconde && first > third ? true:false;
		Boolean b = seconde > first && seconde > third ? true:false;
		System.out.println("max : "+( a == true ? first : (b == true ? seconde : third)));
		/* GPT 가 말하는 더 좋은 코드 
		 * Boolean a = first > seconde && first > third;
		   Boolean b = seconde > first && seconde > third;
		 */
		
		/*입력변수 하나에 받고 내부적으로 최대값을 받게 하기 위해서는 비교변수 한개에 하기 */
		System.out.print("==============================================================");
		int input = sc.nextInt();
		int max = input;
		input = sc.nextInt();
		max = max > input ? max :input; 
		input = sc.nextInt();
		max = max > input ? max :input; 
		System.out.println("max : "+ input);
	}

}
