package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _9251_dp {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int aLength = a.length();
        int bLength = b.length();

        int[][] dp = new int[aLength + 1][bLength + 1];

        for (int i = 1; i <= aLength; i++) {
            for (int j = 1; j <= bLength; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    // x 번째 원소와 y 번째 원소가 같다면 (x - 1, y - 1) 의 LCS 길이 + 1 이다.
                    // == 대각 선 위 (i - 1, j - 1) 의 dp  + 1로 갱신
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 같지 않는 경우, 둘 중 큰 값으로 초기화
                    // == 이전 열  (i - 1) 과 이전 행 (j - 1) 의 값 중 큰 것으로 갱신
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[aLength][bLength]);
    }
}
