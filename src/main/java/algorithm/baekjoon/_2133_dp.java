package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://january-diary.tistory.com/entry/BOJ-2133-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0-JAVA
public class _2133_dp {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 홀수는 구할 수 없다.
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;

        // 짝수 칸만 의미 있음
        for (int i = 4; i <= n; i += 2) {
            long sum = 0;

            //  점화식의 첫 항: 3*dp[n-2]
            sum += 3 * dp[i - 2];

            //  점화식의 나머지 항들: 2*(dp[n-4] + dp[n-6] + ... + dp[0])
            for (int k = i - 4; k >= 0; k -= 2) {
                sum += 2 * dp[k];
            }
            dp[i] = (int) sum;
        }
        System.out.println(dp[n]);
    }
}
