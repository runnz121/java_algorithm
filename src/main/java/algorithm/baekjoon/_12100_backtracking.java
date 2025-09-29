package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12100_backtracking {

    static int N;
    static int[][] maps;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static int answer = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        maps = new int[N][N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {

                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0,  maps);
    }

    static void backTracking(int depth, int[][] maps) {

        // 종료조건
        if (isTerminate() == false && depth == 5) {
            answer = Math.max(maxTitle(maps), answer);
            return;
        }

        // 이동 (상하좌우 모두 확인)
        for (int dir = 0; dir < 4; dir++) {
            move(dir);
        }

        backTracking();
    }

    static void move(int dir) {

        // 상
        if (dir == 0) {

        }
        // 하
        else if (dir == 1) {

        }
        // 좌
        else if (dir == 2) {

        }
        // 우
        else {

        }
    }

    static int maxTitle(int[][] maps) {

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(maps[i][j], max);
            }
        }

        return max;
    }

    static boolean isTerminate(int count) {


        boolean toGo = false;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {

                // 1. 빈 칸이 하나라도 있는 경우
                if (maps[y][x] == 0) {
                    toGo = true;
                    return true;
                }

                // 2. 현재 칸의 상하좌우 중 같은게 하나라도 있는 경우
                for (int k = 0; k < 4; k++) {
                    int ny = y + dy[k];
                    int nx = x + dx[k];

                    // 범위 안에 있는 것들 중에서
                    if (ny >= 0 && nx >= 0 && ny < N && nx < N) {

                        int target = maps[ny][nx];
                        if (maps[y][x] == target) {
                            toGo = true;
                            return true;
                        }
                    }
                }
            }
        }

        if (toGo == false) {
            return true;
        }
        return false;
    }
}
