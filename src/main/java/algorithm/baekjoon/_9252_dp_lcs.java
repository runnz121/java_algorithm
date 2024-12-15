package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 9251 과 연관문제
public class _9252_dp_lcs {

    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int aLength = a.length();
        int bLength = b.length();

        dp = new int[aLength + 1][bLength + 1];

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int maxLength = dp[aLength][bLength];
        if (maxLength == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(maxLength);
        // dp 값이 변하는 부분만 저장
        toLcsString(a, aLength, bLength);
        System.out.println(sb.toString());
    }

    // dp 하향식으로 계산했음으로 역방향으로 이동
    static void toLcsString(String str, int i, int j) {
        Deque<Character> stack = new ArrayDeque<>();

        while (i > 0 && j > 0) {
            if (i == 0 || j == 0) break;

            // y 축 이랑 같은 경우
            if (dp[i][j] == dp[i - 1][j]) {
                i --;
            }
            // x 축이랑 같은 경우
            else if (dp[i][j] == dp[i][j - 1]) {
                j --;
            }
            // dp 의 값이 변화하는경우
            else {
                stack.push(str.charAt(i - 1));
                i --;
                j --;
            }
        }
        while(stack.isEmpty() == false) {
            sb.append(stack.pop());
        }
    }
}
