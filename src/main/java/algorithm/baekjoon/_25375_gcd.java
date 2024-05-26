package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _25375_gcd {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            long a = Long.parseLong(inputs[0]);
            long b = Long.parseLong(inputs[1]);

            sb.append(a * 2 <= b && b % a == 0 ? 1 : 0).append('\n');
        }
        System.out.println(sb);
    }
}
