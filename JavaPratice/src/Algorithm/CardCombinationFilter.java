package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CardCombinationFilter {
	static int[] inputArray ;
	static int K;
	static Integer[] comArray;
	static List<Integer[]> intList = new ArrayList<Integer[]>(); ;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//배열생성
		inputArray = new int [N];
		//배열데이터 넣기		
		for(int i = 0 ; i < inputArray.length ; i++) {
			inputArray[i] = i+1;
		}
		K = sc.nextInt();
		comArray = new Integer[K];
		combination(0,0);
		for( Integer[] i : intList) {
			for(Integer j : i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
	
	static void combination(int start , int depth) {
		//basecase
		if(K == depth) {
			intList.add(comArray);
			return; 
		}
		//조합만들기
		for(int i = start ; i < inputArray.length ; i++) {
			for (Integer[] arr : intList) {
			    System.out.println(Arrays.toString(arr));  // 배열의 상태 확인용
			}
			comArray[depth] = inputArray[i];
			combination( i+1 , depth+1 );
		}
		
	}
}
