package java_harshly;

import java.util.Scanner;

public class Pratice_10_3 {
/*�����̹� ��� �ٵ� ù��° ���� ��ʸ�Ʈ ���..
 * 1. �Է°� �ޱ�
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int first = sc.nextInt();
		int seconde = sc.nextInt();
		int third = sc.nextInt();
		Boolean a = first > seconde && first > third ? true:false;
		Boolean b = seconde > first && seconde > third ? true:false;
		System.out.println("max : "+( a == true ? first : (b == true ? seconde : third)));
		/* GPT �� ���ϴ� �� ���� �ڵ� 
		 * Boolean a = first > seconde && first > third;
		   Boolean b = seconde > first && seconde > third;
		 */
		
		/*�Էº��� �ϳ��� �ް� ���������� �ִ밪�� �ް� �ϱ� ���ؼ��� �񱳺��� �Ѱ��� �ϱ� */
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
