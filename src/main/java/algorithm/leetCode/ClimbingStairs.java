package algorithm.leetCode;

public class ClimbingStairs {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {

        ClimbingStairs cs = new ClimbingStairs();
        int i1 = cs.climbStairs(3);
        System.out.println(i1);
    }
}
