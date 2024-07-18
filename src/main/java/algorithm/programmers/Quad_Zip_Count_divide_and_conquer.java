package algorithm.programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/68936
public class Quad_Zip_Count_divide_and_conquer {

    static int[][] board;
    static int[] answer;

    public int[] solution(int[][] arr) {
        answer = new int[]{0, 0};

        board = arr;

        divide(0, 0, arr.length);

        System.out.println(Arrays.toString(answer));

        return answer;
    }

    void divide(int dx, int dy, int line) {

        if (check(dx, dy, line)) {
            answer[board[dx][dy]]++;
            return;
        }

        divide(dx, dy, line / 2);
        // 2 사분면
        divide(dx, dy + line / 2, line / 2);
        // 3 사분면
        divide(dx + line / 2, dy, line / 2);
        // 4 사분면
        divide(dx + line / 2, dy + line / 2, line / 2);
    }

    static boolean check(int dx, int dy, int line) {
        for (int i = dx; i < dx + line; i++) {
            for (int j = dy; j < dy + line; j++) {
                if (board[dx][dy] != board[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Quad_Zip_Count_divide_and_conquer qzc = new Quad_Zip_Count_divide_and_conquer();
        int[][] inputs = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
        int[][] inputs1 = {
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}
        };
        qzc.solution(inputs);
    }
}
