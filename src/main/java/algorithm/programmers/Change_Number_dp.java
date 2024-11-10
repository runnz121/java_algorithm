package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/154538
public class Change_Number_dp {

    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] dp = new int[y + 1];

        if (x == y) {
            return 0;
        }

        for (int i = x; i <= y; i++) {
            if (i != x && dp[i] == 0) {
                dp[i] = -1;
                continue;
            }
            // dp 배열에 연산 수 할당
            if (i + n <= y) {
                dp[i + n] = (dp[i + n] == 0) ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i + n]);
            }
            if (i * 2 <= y) {
                dp[i * 2] = (dp[i * 2] == 0) ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i * 2]);
            }
            if (i * 3 <= y) {
                dp[i * 3] = (dp[i * 3] == 0) ? dp[i] + 1 : Math.min(dp[i] + 1, dp[i * 3]);
            }
        }

        answer = dp[y];
        return answer;
    }

    public static void main(String[] args) {
        Change_Number_dp cnd = new Change_Number_dp();
        cnd.solution(10, 40, 5);
//        cnd.solution(2, 5, 4);
    }
}
