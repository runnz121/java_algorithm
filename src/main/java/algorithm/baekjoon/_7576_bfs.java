package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _7576_bfs {

    static int N;
    static int M;
    static int[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int sy, sx;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                // 토마토가 여러개 일 수 있음
                if (graph[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        bfs(queue);

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                answer = Math.max(graph[x][y], answer);
                if (graph[x][y] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(answer - 1);
    }

    static void bfs(Queue<int[]> que) {

        while (que.isEmpty() == false) {

            int[] poll = que.poll();
            int x = poll[1];
            int y = poll[0];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx > M - 1 || ny > N - 1) continue;;
                if (graph[ny][nx] != 0) continue;

                // 누적합으로 갱신
                graph[ny][nx] = graph[y][x] + 1;
                que.add(new int[]{ny, nx});
            }
        }
    }
}
