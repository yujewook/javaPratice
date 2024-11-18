package java_harshly;

import java.util.Scanner;

public class Pratice_9_2 {
/*
 * 1. 초를 입력 받는다
 * 2. 시을 초로 계산한 식의 변수1, 분을 초로 계산한 식의 변수2를 할당한다
 * 3. 입력 받은 초를 변수1의 나누기의 몫은 시간에 할당 
 * 4. 나머지는 분으로 계산한 변수2에 할당 나머지는 초에 할당 한다 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		int hour = 60*60;
		int min = 60;
		System.out.printf("%d 초는 %02d시간 %02d분 %02d초입다." , input, input/hour, (input % hour)/min , input % min);
	}

}
