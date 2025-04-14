package Algorithm;

public class PowerSet {
    private static char[] data = {'a', 'b', 'c', 'd', 'e', 'f'};
    private static int n = data.length;
    private static boolean[] include = new boolean[n];

    public static void main(String[] args) {
        powerSet(0);
    }

    public static void powerSet(int k) {
        if (k == n) {
            // 종료 조건: 하나의 부분집합이 완성되었을 때
            for (int i = 0; i < n; i++) {
                if (include[i]) System.out.print(data[i]);
            }
            System.out.println(); // 개행
            return;
        }

        // ① 현재 원소 포함하지 않음
        include[k] = false;
        powerSet(k + 1);

        // ② 현재 원소 포함함
        include[k] = true;
        powerSet(k + 1);
    }
}