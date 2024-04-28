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
