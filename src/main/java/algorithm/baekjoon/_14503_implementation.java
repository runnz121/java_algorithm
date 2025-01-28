package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14503_implementation {

    static int N, M;
    static int r, c, d;
    static int[][] map;
    // 북, 서, 남, 동
    // 0, 3, 2, 1
    // 북 -> 서 -> 남 -> 동
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int count = 0;
    static int backDirections = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 현재칸 청소 안되어있으면 청소한다
        if (map[r][c] == 0) {
            map[r][c] = -1;
            count += 1;
        }

        // 주면 4칸 중 청소되지 되지 않은 빈칸이 없는지 확인
        boolean isAllClean = checkAllCleans(r, c);

        // 모두 청소 되었을 경우
        if (isAllClean) {
            // 바라보는 방향 유지한채 뒤로 한칸 후진
            if (d == 0) {
                backDirections = 2;
            } else if (d == 1) {
                backDirections = 3;
            } else if(d == 2) {
                backDirections = 0;
            } else {
                backDirections = 1;
            }

            // 바라보는 방향의 뒤쪽이 벽인지 확인
            boolean isWall = checkWall(r - dx[backDirections], c - dy[backDirections]);

            // 벽인 경우 출력하고 종료
            if (isWall) {
                System.out.println(count);
                return;
            }

            // 벽이 아닌 경우 이동


        }


        System.out.println(count);
    }

    static boolean checkAllCleans(int x, int y) {

        for (int i = 0; i < 4; i++) {

            int ddx = x + dx[i];
            int ddy = y + dy[i];
            if (map[ddx][ddy] == 0) return false;

        }
        return true;
    }

    static boolean checkWall(int x, int y) {
        if (x < 0 || y < 0 || x > N || y > M || map[x][y] == 1) return true;
        return false;
    }

}
