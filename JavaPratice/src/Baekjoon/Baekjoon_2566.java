package Baekjoon;

import java.util.Scanner;

public class Baekjoon_2566 {

	public static void main(String[] args) {
		int [][] array = new int[9][9];
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				int result = sc.nextInt();
				if(result >= 0 && result <= 100 ) {
					array[i][j] = result;
				} else {
					j--;
				}
					
			}
		}
		sc.close();
		
		int max = 0 ;
		int row = 0 ;
		int colum = 0 ;
		
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				if(array[i][j] > max ) {
					max = array[i][j];
					row = i+1 ;
					colum = j+1 ;	
				}
			}
		}
		System.out.println(max);
		System.out.print(row + " " + colum);
	}

}
/*import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int i,j;
		int max = 0;
		int x = 1, y = 1;
		
		int arr[][] = new int[9][9];
		
		for(i=0;i<9;i++) {
			for(j=0;j<9;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(i=0;i<9;i++) {
			for(j=0;j<9;j++) {
				if(arr[i][j]>max) {
					max = arr[i][j];
					x = i+1;
					y = j+1;
				}
			}
		}
		sc.close();
		
		System.out.println(max);
		System.out.println(x+" "+y);
	}
}*/
