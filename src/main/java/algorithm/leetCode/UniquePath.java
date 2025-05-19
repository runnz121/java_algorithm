package algorithm.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

class UniquePath {

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    static int[][] graph;

    public int uniquePaths(int m, int n) {

        boolean[][] seen = new boolean[m][n];

        graph = new int[m][n];
        graph[0][0] = 1;

        return bfs(m, n, seen);
    }

    private int bfs(int m, int n, boolean[][] seen) {

        Deque<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{0, 0});

        while (que.isEmpty() == false) {

            int[] pop = que.poll();

            int y = pop[0];
            int x = pop[1];

            for (int i = 0; i < 2; i++) {

                int yy = y + dy[i];
                int xx = x + dx[i];

                // 범위벗어나면 패스
                if (yy < 0 || xx < 0 || yy >= m || xx >= n) {
                    continue;
                }

                graph[yy][xx] += graph[y][x];

                if (seen[yy][xx] == false) {
                    que.offer(new int[]{yy, xx});
                    seen[yy][xx] = true;
                }

            }
        }

        return graph[m - 1][n - 1];
    }

}
