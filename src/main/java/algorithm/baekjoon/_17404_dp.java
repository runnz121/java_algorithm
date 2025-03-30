package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _17404_dp {

    static int N;
    static int[][] rgb;
    static int[][] dp;
    static int answer = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        rgb = new int[N + 1][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            rgb[i][0] = a;
            rgb[i][1] = b;
            rgb[i][2] = c;
        }

        // 빨 파 초 3번 확인
        for (int idx = 0; idx < 3; idx++) {

            // 각 집의 색깔을 고정함 (초기화) --> 고정 후 나머지는 최대값 할당
            for (int j = 0; j < 3; j++) {
                if (j == idx) {
                    dp[0][j] = rgb[0][j];
                } else {
                    dp[0][j] = 987654321;
                }
            }

            // 각 색깔에 대해 점화식 고려 -> dp[i + 1] 조건의 경우 이전 집과 다른 색깔로 칠하도록 강제
            for (int i = 1; i < N; i++) {
                dp[i][0] = rgb[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = rgb[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = rgb[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            // 마지막 부분의 고려
            for (int k = 0; k < 3; k++) {
                if (k == idx) continue;
                answer = Math.min(answer, dp[N - 1][k]);
            }
        }
        System.out.println(answer);
    }
}
