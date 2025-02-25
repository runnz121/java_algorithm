package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _16400_dp {

    static List<Integer> PRIMES = new ArrayList<>();
    static int n;
    static int[] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[n + 1];

        // 에라토스테네스 채
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            // 현재 숫자가 소수일 경우 해당 배수 모두 false 처리
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int k = 2; k <= n; k++) {
            if (isPrime[k]) {
                PRIMES.add(k);
            }
        }

        // dp 배열로 생성
        dp = new int[n + 1];
        dp[0] = 1;

        for (int prime : PRIMES) {
            for (int j = prime; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - prime]) % 123456789;
            }
        }

        System.out.println(dp[n]);
    }
}
