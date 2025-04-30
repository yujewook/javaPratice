package Baekjoon;

import java.util.Scanner;

/*
 * 문제: 백준 1018번 - 체스판 다시 칠하기
 * 링크: https://www.acmicpc.net/problem/1018
 *
 * 문제 요약:
 * - 크기가 N×M인 보드가 주어진다. (8 ≤ N, M ≤ 50)
 * - 각 칸은 'W'(흰색) 또는 'B'(검은색)로 칠해져 있다.
 * - 이 보드에서 8×8 크기의 체스판을 잘라내어, 다시 칠해야 하는 최소 칸 수를 구하라.
 * - 체스판은 'W'와 'B'가 번갈아야 한다.
 *
 * 풀이 방향:
 * - 가능한 모든 8×8 구간을 탐색한다.
 * - (W로 시작하는 체스판)과 (B로 시작하는 체스판) 두 가지 경우를 모두 검사한다.
 * - 잘못된 칸 수를 세고, 최소값을 출력한다.
 */

public class Baekjoon1018 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] board = {
            {'B','W','B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W','B','W'}, 
            {'W','B','B','B','W','B','W','B','W','B'}, // 깨진 부분
            {'B','W','B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B','W','B'},
            {'B','W','B','W','B','W','B','W','B','W'},
            {'W','B','W','B','W','B','W','B','W','B'}
        };

        char[][] chesBoard = new char[8][8];
        for (int i = 0; i < chesBoard.length; i++) {
            for (int j = 0; j < chesBoard.length; j++) {
                chesBoard[i][j] = board[i][j];
            }
        }

        // -----------------------------------
        // 🧠 [내가 처음 시도한 방식: 앞 칸 비교해서 수정]
        //
        // - 위쪽 칸, 왼쪽 칸과 비교해서
        //   같은 색이면 바꿔주는 방식으로 count를 셌다.
        //
        // - 깨진 보드가 주어질 때는
        //   기준이 무너져서 틀릴 수 있다는 문제점이 있었다.
        //
        // - 당시 작성한 흐름:
        //
        /*
        int count = 0;
        for (int i = 0; i < chesBoard.length; i++) {
            for (int j = 0; j < chesBoard.length; j++) {
                if (i != 0) { // 첫 행이 아니라면 위쪽 칸 비교
                    if (chesBoard[i][0] == chesBoard[i-1][0]) {
                        // 색이 같으면 변경
                        chesBoard[i][0] = (chesBoard[i-1][0] == 'B') ? 'W' : 'B';
                        count++;
                    }
                }
                if (j != 0) { // 첫 열이 아니라면 왼쪽 칸 비교
                    if (chesBoard[i][j] == chesBoard[i][j-1]) {
                        // 색이 같으면 변경
                        chesBoard[i][j] = (chesBoard[i][j-1] == 'B') ? 'W' : 'B';
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
        */
        //
        // - 문제점:
        //   - 앞 칸을 따라가기 때문에,
        //     보드가 중간에 깨지면 기준 자체가 무너진다. -> B나 W가 아닐시 
        //   - 따라서 체스판의 "고정 규칙"에 맞춰야 한다고 판단했다.
        // -----------------------------------

        // -----------------------------------
        // ✅ [최종 정석 풀이: (i+j)%2 규칙 기반]
        //
        int count1 = 0; // W로 시작
        int count2 = 0; // B로 시작

        for (int i = 0; i < chesBoard.length; i++) {
            for (int j = 0; j < chesBoard.length; j++) {
                if ((i + j) % 2 == 0) {
                    if (chesBoard[i][j] != 'W') count1++;
                    if (chesBoard[i][j] != 'B') count2++;
                } else {
                    if (chesBoard[i][j] != 'B') count1++;
                    if (chesBoard[i][j] != 'W') count2++;
                }
            }
        }

        System.out.println(Math.min(count1, count2));
        // -----------------------------------
    }
}
