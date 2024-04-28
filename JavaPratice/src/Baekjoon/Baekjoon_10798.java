package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10798 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] array = new String[5];
        
        // Input strings
        for (int i = 0; i < array.length; i++) {
            String input = sc.nextLine();
            array[i] = input;
        }
        sc.close();

        StringBuilder sb = new StringBuilder();

        // Determine the maximum length among all input strings
        int maxLength = 0;
        for (String str : array) {
            if (str.length() > maxLength) {
                maxLength = str.length();
            }
        }

        // Iterate through each character position
        for (int i = 0; i < maxLength; i++) {
            // Iterate through each string in the array
            for (String str : array) {
                // Check if the current string has character at the current position
                if (i < str.length()) {
                    // Append the character to the result StringBuilder
                    sb.append(str.charAt(i));
                }
            }
        }

        // Print the final result
        System.out.println(sb.toString());
    }

}
/*package Baekjoon;

import java.util.Scanner;

public class Baekjoon_10798 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String [] array = new String[3];
		for(int i = 0 ; i < array.length ;i++) {
			String input =sc.nextLine();
			array[i] = input;
		}
		sc.close();	
		int i =0;
		
		StringBuilder sb = new StringBuilder();
		int maxLength = 0;
		
		//단어의 숫자
		while(i < array.length ) {
			
			String output = array[i];
	        
			//단어안의 문자갯수
			for (int j = 0 ; j < output.length() ; j++) {
				char insertChar  = output.charAt(j);
				if(i == 0 ) {
				  sb.insert( j+i , insertChar);
				} else {
				  	
				}
				
			}
			System.out.println("중간 "+ sb.toString());
			i++;
		}
		
		System.out.println(sb.toString());
		
		

	}

}
*/
