package algorithm.programmers;

import java.util.Arrays;

public class Fibonacci {

    static int[] dp;

    public int solution(int n) {
        int answer = 0;

        dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567;
        }

        answer = dp[n];
        return answer % 1234567;
    }

    public static void main(String[] args) {
        Fibonacci fb = new Fibonacci();
        fb.solution(5);
    }
}
