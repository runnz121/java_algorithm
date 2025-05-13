package algorithm.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfIslands {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    int xLength;
    int yLength;


    public int numIslands(char[][] grid) {

        xLength = grid[0].length;
        yLength = grid.length;
        int answer = 0;

        visited = new boolean[yLength][xLength];

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[j][i] == '1' && visited[j][i] == false) {
                    answer += bfs(i, j, grid);
                }
            }
        }

        return answer;
    }

    private int bfs(int x, int y, char[][] grid) {

        Deque<int[]> que = new ArrayDeque<>();
        que.push(new int[]{y, x});
        visited[y][x] = true;

        while (que.isEmpty() == false) {

            int[] pop = que.pop();

            int yy = pop[0];
            int xx = pop[1];

            for (int i = 0; i < 4; i++) {

                int newY = yy + dy[i];
                int newX = xx + dx[i];

                if (newY >= yLength || newX >= xLength || newY < 0 || newX < 0) {
                    continue;
                }

                if (grid[newY][newX] != '1') {
                    continue;
                }

                if (visited[newY][newX] == false) {
                    que.push(new int[]{newY, newX});
                    visited[newY][newX] = true;
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        NumberOfIslands nos = new NumberOfIslands();
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        int i1 = nos.numIslands(grid2);
        System.out.println(i1);
    }
}
