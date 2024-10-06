package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//������2
public class Baekjoon_11660 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer strToken = new StringTokenizer(br.readLine());
        
        // �迭 ũ��, ���� ���� �Է�
        int arraySize = Integer.parseInt(strToken.nextToken());
        int quiz = Integer.parseInt(strToken.nextToken());

        int[][] inputDatarray = new int[arraySize][arraySize];
        int[][] sumArray = new int[arraySize + 1][arraySize + 1];

        // ������ �ֱ�
        for (int i = 0; i < arraySize; i++) {
            strToken = new StringTokenizer(br.readLine());
            for (int j = 0; j < arraySize; j++) {
                inputDatarray[i][j] = Integer.parseInt(strToken.nextToken());
            }
        }

        // �迭�� �����
        for (int i = 1; i <= arraySize; i++) {
            for (int j = 1; j <= arraySize; j++) {
                sumArray[i][j] = sumArray[i - 1][j] + sumArray[i][j - 1] 
                               - sumArray[i - 1][j - 1] + inputDatarray[i - 1][j - 1];
            }
        }

        // �迭�� ��� (����� �뵵)
        for (int i = 0; i <= arraySize; i++) {
            for (int j = 0; j <= arraySize; j++) {
                System.out.print(sumArray[i][j] + "\t");
            }
            System.out.println();
        }

        // ���� ó��
        for (int q = 0; q < quiz; q++) {
            strToken = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(strToken.nextToken());
            int y1 = Integer.parseInt(strToken.nextToken());
            int x2 = Integer.parseInt(strToken.nextToken());
            int y2 = Integer.parseInt(strToken.nextToken());

            // ������ ���
            int result = sumArray[x2][y2] - sumArray[x1 - 1][y2] 
                       - sumArray[x2][y1 - 1] + sumArray[x1 - 1][y1 - 1];

            System.out.println(result);
        }
    }
}
