package java_harshly;

import java.util.Scanner;

public class Pratice_12_1 {
/*총합계산기
 * 1. while 문으로 1~10
 * 2. for 문으로 1~10
 * 3. 총합누적 하는 변수 활용 
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int cnt = 0;
		
		while(cnt < 10) {
			sum += ++cnt;
		}
		System.out.println("while total:"+sum);
		
		sum = 0;
		for(int i = 1 ; i < 11 ; i++) {
			sum += i;
		}
		System.out.println("for total:"+sum);
	}

}
