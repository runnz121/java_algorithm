package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3197
public class _3197_bfs_disjointSet {

    static int R, C, ex, ey;
    static char [][] graph;
    static boolean[][] visited;
    static Queue<int[]> wq, sq;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];

        // 물을 담는 큐
        wq = new LinkedList<>();
        // 백조 위치를 담는 큐
        sq = new LinkedList<>();

        int sx = -1;
        int sy = -1;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = line.charAt(j);
                if (graph[i][j] == 'L') {
                    // 첫번째 백조 위치
                    if (sx == -1 && sy == -1) {
                        sx = j;
                        sy = i;
                    } else {
                    // 두번째 백조 위치
                        ex = j;
                        ey = i;
                    }
                    // 백조는 물 위에 존재함으로 백조를 물로 치환
                    graph[i][j] = '.';
                }

                if (graph[i][j] == '.') {
                    wq.add(new int[] {j, i});
                }
            }
        }

        visited = new boolean[R][C];
        sq.add(new int[] {sx, sy});
        visited[sy][sx] = true;

        int time = 0;
        while (true) {
            if (move()) break;
            melting();
            time++;
        }
        System.out.println(time);
    }

    static boolean move() {
        Queue<int[]> que = new LinkedList<>();
        while (sq.isEmpty() == false) {
            int[] p = sq.poll();
            int px = p[0], py = p[1];
            if (px == ex && py == ey) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || ny < 0 || nx > C - 1 || ny > R - 1 || visited[ny][nx]) continue;

                visited[ny][nx] = true;

                if (graph[ny][nx] == '.') {
                    sq.add(new int[] {nx, ny});
                } else if (graph[ny][nx] == 'X') {
                    que.add(new int[] {nx, ny});
                }
            }
        }

        // 위치 갱신
        sq = que;
        return false;
    }

    static void melting() {
        int size = wq.size();
        for (int i = 0; i < size; i++) {
            int[] p = wq.poll();

            for (int j = 0; j < 4; j++) {
                int nx = p[0] + dx[j];
                int ny = p[1] + dy[j];

                if (nx < 0 || ny < 0 || nx > C - 1 || ny > R - 1) continue;
                if (graph[ny][nx] == 'X') {
                    graph[ny][nx] = '.';
                    wq.add(new int[] {nx, ny});
                }
            }
        }
    }
}
