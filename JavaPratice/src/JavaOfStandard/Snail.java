package JavaOfStandard;

import java.util.Scanner;

public class Snail {

	static Boolean snailCheck(int[][] a) {
		Boolean confirm = true;
		for (int i = 0 ; i < a.length ; i++) {
			for (int j = 0 ; j < a.length ; j++) {
				if (a[i][j] == 0) {
					confirm = false;
				}
			}
		}
		return confirm;
	}
	
	public static void main(String[] args) {
		System.out.println("�������� �� ���� �� ġ�� ����");
		Scanner sc = new Scanner(System.in);
		
		int count = sc.nextInt();
		int snail [][] = new int[count][count];
		int row = 0; //��
		int col = 0; //��
		int rowEnd = count-1;	//��輱 �ε���
		int colEnd = count-1;   //��輱 �ε���
		int result = 1; // ��
		
		do{
			//�� -> �� (���̴þ)
			for(int i = col; i <= colEnd ; i++  ) {
				snail[row][i] = result ++;
			}
			row++; // �� -> �� ������ �� ������ �Ʒ� �� �ϳ� �ؿ��� ����
			
			//������ �Ʒ� �� ������ �þ
			for( int i = row ; i <= colEnd ; i++ ) {
				snail[i][colEnd] = result++;
			}
			colEnd--; //��輱 ������
			
			//�� <- �� (���� �þ)
			if(row <= rowEnd) {
				for(int i = colEnd ; i >= col  ; i-- ) {
					snail[rowEnd][i] = result++;
				}
				rowEnd--;
			}
			
			//�Ʒ����� �� (���̴þ)
			if(col <= colEnd) {
				for(int i = rowEnd ; i >= row  ; i-- ) {
					snail[i][col] = result++;
				}
				col++;
			}
		
		}while(!snailCheck(snail)); 
		
        // ��� ���
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail[i].length; j++) {
                System.out.print(snail[i][j] + "\t");
            }
            System.out.println();
        }
	}

}
