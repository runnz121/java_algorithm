package algorithm.baekjoon;

import java.io.*;

/**
 * 이전 자리수의 9 ~ 9까지의 합
 *
 * dp[i][j] = dp[i-1][j] + dp[i-j][j+1]
 *
 */
public class _2688_dynamic_programming {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 맥스 64 자리 1 ~ 9까지 의 수
        long[][] dp = new long[65][10];

        // 1자리는 모두 1개
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // N자리의 S로 시작하는 수에 대한 줄어들지 않는 수의 개수 = (N - 1)자리의 S로 시작하는 수부터 (N - 1)자리의 9까지의 줄어들지 않는 수의 합
        for (int i = 2; i <= 64; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            long ans = 0;

            for (int i = 0; i <= 9; i++) {
                ans += dp[N][i];
            }

            sb.append(ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
