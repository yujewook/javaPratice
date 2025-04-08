package Algorithm;

public class CountingCell {
    private static final int BACKGROUND_COLOR = 0;
    private static final int IMAGE_COLOR = 1;
    private static final int ALREADY_COUNTED = 2;
    
    private static final int N = 5; // Grid size
    private static int[][] grid = {
        {0, 0, 1, 0, 0},
        {0, 1, 1, 1, 0},
        {1, 1, 0, 1, 1},
        {0, 1, 1, 1, 0},
        {0, 0, 1, 0, 0}
    };

    public int countCell(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N)
            return 0;
        else if (grid[x][y] != IMAGE_COLOR)
            return 0;
        else {
            grid[x][y] = ALREADY_COUNTED;
            return 1 + countCell(x - 1, y + 1) + countCell(x, y + 1)
                    + countCell(x + 1, y + 1) + countCell(x - 1, y)
                    + countCell(x + 1, y) + countCell(x - 1, y - 1)
                    + countCell(x, y - 1) + countCell(x + 1, y - 1);
        }
    }

    public static void main(String[] args) {
        CountingCell cc = new CountingCell();
        int startX = 2, startY = 2; // Starting point (2,2) in the grid
        int cellCount = cc.countCell(startX, startY);
        System.out.println("The number of connected cells: " + cellCount);
    }
}
