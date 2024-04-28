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
		//색종이 갯수
		int totalCount = sc.nextInt();
        // 사각형 넣을 배열
        Rectangle[] rectangles = new Rectangle[totalCount];
		
		//사각형 범위
		for(int i = 0 ; i < totalCount ; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			rectangles[i] = new Rectangle(x, y);
		}
		
		int overlapArea = 0;
		//중복되는 로직
		for(int i = 0 ; i < totalCount ; i++) {
            for (int j = i + 1; j < totalCount; j++) {
                if (rectangles[i].isOverLapping(rectangles[j])) {
                    // 중복되는 부분이 있을 경우, 중복된 넓이를 계산하여 합산
                    overlapArea += calculateOverlapArea(rectangles[i], rectangles[j]);
                }
            }
		}
		
		System.out.println("중복되는 부분의 넓이: " + overlapArea);
		System.out.println((10*10*totalCount)- overlapArea);
	}

    // 두 직사각형의 중복된 부분의 넓이를 계산하는 메서드
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
        int total = 0;  //검은 영역의 넓이
        int n = Integer.parseInt(br.readLine());  //색종이 개수
        boolean[][] arr = new boolean[101][101];  //도화지
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //(x,y)부터 (x+9, y+9)까지의 영역을 하나씩 체크한 후 총 넓이에 더해준다
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
