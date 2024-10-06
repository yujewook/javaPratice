package JavaOfStandard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Combinateion {
	
    // 재귀를 이용한 조합 생성
    public static void generateCombinations(int[] arr, int r, int start, List<Integer> current, List<List<Integer>> combinations) {
        if (current.size() == r) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            generateCombinations(arr, r, i + 1, current, combinations);
            current.remove(current.size() - 1); // 마지막 원소 제거
        }
    }
	
	
	public static void main(String[] args) throws IOException {
		/**************************************************************************
		 * 변수선언
		 **************************************************************************/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer strTokener = new StringTokenizer(br.readLine()); 
		int arraySize = Integer.parseInt(strTokener.nextToken());//배열크기
		int quizr = Integer.parseInt(strTokener.nextToken());//조합할 개수
		int inputArray[] = new int[arraySize]; //배열생성
		List<List<Integer>> combinations = new ArrayList<>();
		List<int[]> outputList = new ArrayList<>(); 
		
		/**************************************************************************
		 * 데이터 넣기
		 **************************************************************************/
		for(int i = 0 ; i < inputArray.length ; i++ ) {
			strTokener = new StringTokenizer(br.readLine());
			inputArray[i] = Integer.parseInt(strTokener.nextToken()); 
		}
		System.out.println(Arrays.toString(inputArray));
		
		/**************************************************************************
		 *전체 조합찾기
		 **************************************************************************/
		generateCombinations(inputArray, quizr, 0, new ArrayList<>(), combinations);

		/**************************************************************************
		 * 0 조합찾기
		 **************************************************************************/
	    for (List<Integer> combination : combinations) {
			System.out.println(combination);
			 
			int output = 0;
			for( int i : combination) {
				output += i ;
			}
			if(output == 0 ) {
				int[] combinationArray = combination.stream().mapToInt(Integer::intValue).toArray();
                outputList.add(combinationArray);
			}
	 
	    }
        /**************************************************************************
         * 출력하기
         **************************************************************************/	     
	    System.out.println("0의 조합");
	    for (int[] array : outputList) {
            System.out.println(Arrays.toString(array));
        }
	}

}
