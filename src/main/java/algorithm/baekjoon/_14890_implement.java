package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14890_implement {

    static int N;
    static int M;
    static int[][] MAP;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                MAP[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // y 기준
        for (int y = 0; y < N; y++) {

            for (int x = 1; x < N; x++) {

                // +=1 차이인지 확인
                int delta = Math.abs(MAP[y][x - 1] - MAP[y][x]);

                // 지나갈 수 있는 경우
                if (delta == 1 || delta == 0) {
                    continue;
                }

                // 지나갈 수 없는 경우 경사로를 확인
            }
        }
    }
}
