package algorithm;

import java.util.*;
import java.lang.*;
import java.io.*;

public class _21919 {

    static long []array;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input
        int N = Integer.parseInt(br.readLine()); // 5

        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 2 3 5 8

        array = new long[N];
        long ans = 1;

        for (int i = 0; i < N; i ++) {
            // 값을 입력 받음
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

    // 에라토스테네스 채
    public static boolean check_prime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; ++i) {
            if(num % i == 0) return false;
        }
        return true;
    }

    // 최대공약수
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // 최소공배수 (반복문)
    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // for 문
    // int gcd(int a, int b) {
    //     while (b != 0) {
    //         int r = a % b;
    //         a = b;
    //         b = r;
    //     }
    //     return a;
    // }

}
