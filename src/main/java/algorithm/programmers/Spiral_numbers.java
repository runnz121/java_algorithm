package algorithm.programmers;

import java.util.Arrays;

public class Spiral_numbers {

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public int[][] solution(int n) {
        int[][] answer = new int[n][n];

        int count = 1;
        int idx = 0;
        int lineCount = 1;

        int x = 0;
        int y = 0;
        answer[y][x] = 1;
        while (count < n * n) {

            if (lineCount >= n || (y + dy[idx] >= n || y + dy[idx] < 0 || x + dx[idx] >= n || x + dx[idx] < 0 || answer[y + dy[idx]][x + dx[idx]] != 0)) {

                idx += 1;
                idx = idx % 4;
                lineCount = 1;
            }

            int prev = answer[y][x];

            y += dy[idx];
            x += dx[idx];

            answer[y][x] = prev + 1;

            lineCount += 1;
            count += 1;
        }

        System.out.println(Arrays.deepToString(answer));

        return answer;
    }

    public static void main(String[] args) {
        Spiral_numbers sn = new Spiral_numbers();
        sn.solution(4);
    }
}
