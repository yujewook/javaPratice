package JavaOfStandard;

import java.util.Scanner;

public class pratice {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		
		int arr[] = new int[n];
		
		for (int j = 0 ; j < arr.length ; j++) {
			arr[j] = (int) (Math.random()*n)-1;
		}
		
		for(int j = 0 ; j < arr.length ; j++) {
			System.out.print(arr[j]);
		}
		
		
		for(int j = 0 ; j < arr.length ; j++) {
			boolean check = false;
			
			for(int i = 0 ; i < j ; i++) {
				if(arr[j] == arr[i]) {
					check = true;
					break;
				}
			}
			
			if(check) {
				arr[j] = (int) (Math.random()*n)-1;
				j=-1;
				continue;
			}
		}
		
		for(int j = 0 ; j < arr.length ; j++) {
			System.out.print(arr[j]);
		}
	}

}
