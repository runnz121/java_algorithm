package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/12900
public class NX2_Tile_DP {

    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[60000];

        if (n == 0) {
            return 0;
        }

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < 60000; i++) {
            dp[i] = dp[i - 1]%1000000007 + dp[i - 2]%1000000007;
        }
        answer = dp[n];
        return answer % 1000000007;
    }

    public static void main(String[] args) {
        NX2_Tile_DP np = new NX2_Tile_DP();
        np.solution(4);
    }
}
