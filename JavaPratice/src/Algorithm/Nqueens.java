package Algorithm;

public class Nqueens {

    final int N = 8; // N-Queens problem grid size
    int[] cols = new int[N + 1]; // 1-based index array to track queen's position

    public static void main(String[] args) {
        Nqueens nq = new Nqueens();
        nq.solve();
    }

    void solve() {
        if (queens(0)) {
            printSolution();
        } else {
            System.out.println("No solution found.");
        }
    }

    boolean queens(int level) {
        if (!promising(level)) {
            return false;
        } else if (level == N) { // All queens placed correctly
            return true;
        }

        for (int i = 1; i <= N; i++) { // Try placing queens in each column (1-based index)
            cols[level + 1] = i; // Try placing queen in the next row
            if (queens(level + 1)) {
                return true;
            }
        }
        return false;
    }

    boolean promising(int level) {
        for (int i = 1; i < level; i++) {
            if (cols[i] == cols[level]) { // Check for same column
                return false;
            } else if (level - i == Math.abs(cols[level] - cols[i])) { // Check for diagonal conflict
                return false;
            }
        }
        return true;
    }

    void printSolution() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (cols[i] == j) {
                    System.out.print("Q "); // Print queen
                } else {
                    System.out.print(". "); // Empty space
                }
            }
            System.out.println();
        }
    }
}
