package Baekjoon;

import java.util.Scanner;

class Rectangle {
    int x;
    int y;
    int endX;
    int endY;
    
    public Rectangle(int x, int y) {
        this.x = x;
        this.y = y;
        this.endX = x+10;
        this.endY = y+10;
    }
    
    public boolean isOverLapping(Rectangle other) {
    	return this.x < other.endX &&  this.endX > other.x &&
               this.y < other.endY && this.endY > other.y;
    }
    
}



public class Baekjoon_2563 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//������ ����
		int totalCount = sc.nextInt();
        // �簢�� ���� �迭
        Rectangle[] rectangles = new Rectangle[totalCount];
		
		//�簢�� ����
		for(int i = 0 ; i < totalCount ; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			rectangles[i] = new Rectangle(x, y);
		}
		
		int overlapArea = 0;
		//�ߺ��Ǵ� ����
		for(int i = 0 ; i < totalCount ; i++) {
            for (int j = i + 1; j < totalCount; j++) {
                if (rectangles[i].isOverLapping(rectangles[j])) {
                    // �ߺ��Ǵ� �κ��� ���� ���, �ߺ��� ���̸� ����Ͽ� �ջ�
                    overlapArea += calculateOverlapArea(rectangles[i], rectangles[j]);
                }
            }
		}
		
		System.out.println("�ߺ��Ǵ� �κ��� ����: " + overlapArea);
		System.out.println((10*10*totalCount)- overlapArea);
	}

    // �� ���簢���� �ߺ��� �κ��� ���̸� ����ϴ� �޼���
    public static int calculateOverlapArea(Rectangle r1, Rectangle r2) {
        int overlapWidth = Math.min(r1.endX, r2.endX) - Math.max(r1.x, r2.x);
        int overlapHeight = Math.min(r1.endY, r2.endY) - Math.max(r1.y, r2.y);
        return overlapWidth * overlapHeight;
    }
	
}


/*
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;  //���� ������ ����
        int n = Integer.parseInt(br.readLine());  //������ ����
        boolean[][] arr = new boolean[101][101];  //��ȭ��
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //(x,y)���� (x+9, y+9)������ ������ �ϳ��� üũ�� �� �� ���̿� �����ش�
            for (int j = x; j < x+10; j++) {
                for (int k = y; k < y+10; k++) {
                    if (!arr[j][k]) {
                        arr[j][k] = true;
                        total++;
                    }
                }
            }
        }
        System.out.print(total);
    }
}
*/
