package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    입력
    3 5
    OOOPO
    OIOOX
    OOOXP
 */
public class _21736_bfs {
    static char [][] maps;
    static boolean [][] isVisited;
    static StartPosition start;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());

        // maps
        maps = new char[N][M];
        isVisited = new boolean[N][M];

        // 체크 배열 false 초기화
        for (boolean[] arr : isVisited) {
            Arrays.fill(arr, false);
        }

        for (int i = 0; i < N; i ++) {
            String str = br.readLine();
            for (int j = 0; j < M; j ++) {
                maps[i][j] = str.charAt(j);
                if (str.charAt(j) == 'I') {
                    start = new StartPosition(i, j);
                }
            }
        }

        int res = bfs(start);

        if (res == 0) {
            System.out.println("TT");
            return;
        }
        System.out.println(res);
    }

    static int bfs(StartPosition start) {

        int meetPeople = 0;

        Deque<StartPosition> que = new LinkedList<>();
        isVisited[start.x][start.y] = true;
        que.offer(start);

        while (!que.isEmpty()) {
            StartPosition pos = que.poll();
            int a = pos.x;
            int b = pos.y;

            for (int k = 0; k < 4; k++) {
                int nx = a + dx[k];
                int ny = b + dy[k];
                // 범위 밖일경우 건너 띔
                if (0 > nx || 0 > ny || nx >= N || ny >= M) {
                    continue;
                }
                // 벽인 경우 건너띔
                if (maps[nx][ny] == 'X') {
                    continue;
                }
                // 방문 처리
                if (isVisited[nx][ny]) {
                    continue;
                }
                // 만났을 경우
                if (maps[nx][ny] == 'P') {
                    meetPeople += 1;
                }
                StartPosition cur = new StartPosition(nx, ny);
                que.offer(cur);

                // 방문 처리
                isVisited[nx][ny] = true;
            }
        }
        return  meetPeople;
    }

    static class StartPosition {
        int x;
        int y;

        StartPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
