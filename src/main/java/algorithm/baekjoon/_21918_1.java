package algorithm.baekjoon;

import java.util.*;
import java.lang.*;
import java.io.*;

public class _21918_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long ans = 1;
        for (int i = 0; i < N; i++) {
            long inputX = Long.parseLong(st.nextToken());
            if (check_prime(inputX)) {
                ans = lcm(ans, inputX);
            }
        }
        if (ans == 1)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    public static boolean check_prime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; ++i) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // 최대 공약수
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // 최소 공배수
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static long gcdWhile(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
