package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9658_dynamic {

    static int N;
    static int [] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 순서 : 상근 -> 창영 (1, 0)

        // 1개 : 상근 1 -> 창영
        // 2개 : 상근 1 창영 1 -> 상근
        // 3개 : 상근 1 창영 1 상근 1 -> 창영
        // 4개 : 상근 3 창영 1 -> 상근
        // 5개 : 상근 4 창영 1 -> 상근
        // 6개 : 상근 3 창영 1 상근 1 창영 1 -> 상근
        // 7개 : 상근 4 창영 1 상근 1 창영 1 -> 상근
        // 8개 : 상근 4 창영 3 상근 1 -> 창영
        // 9개 : 상근 4 창영 1 상근 3 창영 1 -> 상근
        // 10개 :상근 4 창영 3 상근 1 창영 1 상근 1-> 창영

        N = Integer.parseInt(st.nextToken());

        dp = new int[1001];

        dp[1] = dp[3] = 0;
        dp[2] = dp[4] = dp[5] = 1;

        // 상근이가 이기는 경우 암은 돌이 1, 3, 4 일 때 상근이가 이기는 경우라면 창영이가 승리한다
        for (int i = 6; i <= N; i++) {
            if (dp[i - 1] == 1 && dp[i - 3] == 1 && dp[i - 4] == 1) {
                dp[i] = 0;
            } else {
                dp[i] = 1;
            }
        }

        if (dp[N] == 1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
