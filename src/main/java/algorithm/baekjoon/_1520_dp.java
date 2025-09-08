package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 접근법
 * 1. 출발점 -> 도착점 경로 개수 구하기
 * 2. 더 낮은 높이만 이동
 * 3. dfs + dp 로 처리
 * 4. shortestpath == y,x 에서 도착지점까지 가는 경로 수
 *
 */
public class _1520_dp {

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int m;
    static int n;

    // 내리막길 경위의 수
    static int[][] shortestPath;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // dp 배열 초기화
        shortestPath = new int[m][n];
        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            Arrays.fill(shortestPath[i], -1);

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = dfs(0, 0);

        System.out.println(answer);
    }

    static int dfs(int y, int x) {

        // 이미 계산된 값이 있는 경우 (-1 이 아닌 값으로 설정 되면 바로 반환)
        if (shortestPath[y][x] != -1) {
            return shortestPath[y][x];
        }

        // 도착점 도달시 1 반환
        if (y == m - 1 && x == n - 1) {
            return 1;
        }

        // 미 방문시 0으로 초기화 후 누적 시작
        shortestPath[y][x] = 0;

        for (int k = 0; k < 4; k++) {

            int newY = y + dy[k];
            int newX = x + dx[k];

            if (newY < 0 || newY >= m || newX < 0 || newX >= n) {
                continue;
            }

            // 내리막만 내려감으로 위는 안된다
            if (map[newY][newX] >= map[y][x]) {
                continue;
            }

            shortestPath[y][x] += dfs(newY, newX);
        }

        // 현재 좌표 (시작지점) 총 가능 거리수 반환
        return shortestPath[y][x];
    }
}
