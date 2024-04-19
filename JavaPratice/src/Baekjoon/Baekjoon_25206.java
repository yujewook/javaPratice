package Baekjoon;

import java.util.Scanner;

public class Baekjoon_25206 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double result =0.0;
		double creditSum =0.0;
		
		for(int i = 0 ; i < 20 ; i++) {
			double credit = 0;
			String str_arr[] = sc.nextLine().split(" ");
			credit = Double.parseDouble(str_arr[1]);
			String hakjom = str_arr[2];
			
			double hakjompoint = 0.0;
			
			
			if(hakjom.equals("A+")) {
				hakjompoint =4.5;
			}else if (hakjom.equals("A0")) {
				hakjompoint =4.0;
			}else if (hakjom.equals("B+")) {
				hakjompoint =3.5;
			}else if (hakjom.equals("B0")) {
				hakjompoint =3.0;
			}else if (hakjom.equals("C+")) {
				hakjompoint =2.5;
			}else if (hakjom.equals("C0")) {
				hakjompoint =2.0;
			}else if (hakjom.equals("D+")) {
				hakjompoint =1.5;
			}else if (hakjom.equals("D0")) {
				hakjompoint =1.0;
			}else if (hakjom.equals("P")) {
				hakjompoint =0.0;
			} else {
				hakjompoint =0.0;
			}
			creditSum += credit;
			result += credit*hakjompoint;

		}
		System.out.println(result/creditSum);
	}

}
