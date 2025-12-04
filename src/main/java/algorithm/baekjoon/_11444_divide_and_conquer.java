package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 *
 * 1. n을 절반(n/2)으로 줄여가며 F(k), F(k+1)을 구한다.
 *
 * 2. 내려가며 구한 F(k), F(k+1)을 이용해
 *    F(2k), F(2k+1)을 즉시 계산하는 doubling 공식을 적용한다.
 *
 * 3. n이 짝수면 (F(n), F(n+1)) = (F(2k), F(2k+1)),
 *    n이 홀수면 (F(n), F(n+1)) = (F(2k+1), F(2k) + F(2k+1)) 로 결정한다.
 *
 *  >> n을 계속 2로 나누고 올라오면서 값을 두 배로 확장하므로 O(log n)으로 F(n) 계산 가능.
 */
public class _11444_divide_and_conquer {

    static long n;
    static long MOD = 1000000007;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());

        long[] res = recursive(n);

        System.out.println(res[0] % MOD);
    }

    static long[] recursive(long n) {

        if (n == 0) {
            return new long[]{0, 1};
        }

        long[] divide = recursive(n / 2);

        long a = divide[0];
        long b = divide[1];

        long twoB = (2 * b) % MOD;               // 2*F(k+1)
        long tmp = (twoB - a + MOD) % MOD;       // (2*F(k+1) - F(k)) mod
        long c = (a * tmp) % MOD;                // F(2k)
        long d = (a * a % MOD + b * b % MOD) % MOD; // F(2k+1)

        // 짝수 일때
        if (n % 2 == 0) {
            return new long[]{c, d};
        }
        // 홀수 일때
        else {
            long e = (c + d) % MOD;
            return new long[]{d, e};
        }
    }
}
