package algorithm.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class _11060 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();

        int [] dp = new int[x + 1];
        Arrays.fill(dp, 0);

        // 10번 돌음
        for (int i = 0; i < x; i ++) {
            int y = sc.nextInt();

            for (int k = i + 1; k <= y + i; k++) {
                if (y + i < x) {
                    if (dp[i] != 0) {
                        dp[k] = dp[i] + 1;
                    } else {
                        dp[k] += 1;
                    }
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt()-1);
    }
}
