package Algorithm;
/*
 * 미로찾기
 * 현재위치에서 출구 까지 있는 경로
 * 
 */
public class Recursion_maze {
	private static int n = 8;
	private static int maze[][] = {
			{0,0,0,0,0,0,0,1},
			{0,1,1,0,1,1,0,1},
			{0,0,0,1,0,0,0,1},
			{0,1,0,0,1,1,0,0},
			{0,1,1,1,0,0,1,1},
			{0,1,0,0,0,1,0,1},
			{0,0,0,1,0,0,0,1},
			{0,1,1,1,0,1,0,0}
	};
	private static final int PATHWAY_COLOUR = 0;
	private static final int WALL_COLOUR = 1;
	private static final int BLOCKED_COLOUR = 2; //출구가 완전히 막힌곳 지우는것
	private static final int PATH_COLOUR = 3; //아직 미정인곳
	
	public static Boolean recursion(int x , int y) {
		if (x < 0 || y <0 || x>=n || y>=n) {
			return false;
		} else if (maze[x][y]!=PATHWAY_COLOUR){
			return false;
		} else if (x==n-1 && y==n-1){
			maze[x][y] = PATH_COLOUR;
			return true;
		} else {
			maze[x][y] = PATH_COLOUR;
			if(recursion(x-1,y) || recursion(x,y+1)||recursion(x+1,y) || recursion(x,y-1)) {
				return true;
			}
			maze[x][y] = BLOCKED_COLOUR;
			return false;
		}
	}
	
    public static void printMaze() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

	public static void main(String[] args) {
		printMaze();
		recursion(0,0);
		System.out.println("실행후");
		printMaze();
	}

}
