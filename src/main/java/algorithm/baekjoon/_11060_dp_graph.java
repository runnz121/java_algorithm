package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _11060_dp_graph {

    static int N;
    static int[] dp;
    static int[] maze;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        // 초기화
        maze = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            maze[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1] = 0;

        // 순차 탐색
        for (int i = 1; i <= N; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                int jump = maze[i];

                // 해당 미로에 할당되어있는 갈수 있는 값들을 계산
                for (int j = 1; j <= jump; j++) {
                    // 최대값보다 넘어가면 패스
                    if (i + j > N) continue;
                    // 최소 점프 횟수를 dp에 저장
                    dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
                }
            }
        }
        System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
    }
}
