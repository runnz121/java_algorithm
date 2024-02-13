package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _14940_bfs {
    static int [][] graph;
    static int [][] distance;
    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean [][] isVisited;
    static int stX, stY;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(st1.nextToken());
        M = Integer.parseInt(st1.nextToken());
        // graph 생성
        graph = new int [N][M];
        distance = new int[N][M];
        isVisited = new boolean[N][M];
        // 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                int x = Integer.parseInt(st.nextToken());
                graph[i][j] = x;
                // 목표지점 설정 및 시작 지점 초기화
                if (x == 2) {
                    stX = i;
                    stY = j;
                }
            }
        }
        // 체크 배열 false 초기화
        for (boolean[] arr : isVisited) {
            Arrays.fill(arr, false);
        }
        // 목표지점으로 bfs
        bfs(stX, stY);

        // 값 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j ++) {
                // 방문처리도 안된 경우 ==> 도달할 수 없는 위치인 경우
                if (!isVisited[i][j] && graph[i][j] == 1) {
                    builder.append(-1 + " ");
                } else {
                    builder.append(distance[i][j] + " ");
                }
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }

    static void bfs(int i, int j) {
        // 큐 생성
        Queue<Current> que = new LinkedList<>();
        Current cur = new Current(stX, stY);
        // 큐 삽입
        que.offer(cur);
        isVisited[i][j] = true;
        // bfs 탐색 시작
        while (!que.isEmpty()) {
            Current pos = que.poll();
            int a = pos.x;
            int b = pos.y;
            // 4방향 탐색 진행
            for (int k = 0; k < 4; k++) {
                int nx = a + dx[k];
                int ny = b + dy[k];
                // 4방향 범위에서 벗어나면 패스
                if (0 > nx || 0 > ny || nx >= N || ny >= M ) {
                    continue;
                }
                // 0 인 경우 건너띔
                if (graph[nx][ny] == 0) {
                    continue;
                }
                // 이미 방문 했을 경우 건너띔
                if (isVisited[nx][ny]) {
                    continue;
                }
                // 큐에 추가
                Current posUpdate = new Current(nx, ny);
                que.offer(posUpdate);
                // 거리 배열을 현재 값에 1더한걸로 초기화
                distance[nx][ny] = distance[pos.x][pos.y] + 1;
                // 해당 부분을 방문 처리함
                isVisited[nx][ny] = true;
            }
        }
    }

    static class Current {
        int x;
        int y;

        Current(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }
}
