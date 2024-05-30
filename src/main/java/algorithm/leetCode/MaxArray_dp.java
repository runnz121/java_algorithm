package algorithm.leetCode;

public class MaxArray_dp {

    static int[] dp;
    static int maxValue;

    public int maxSubArray(int[] nums) {

        int size = nums.length;

        dp = new int[size];
        dp[0] = nums[0];
        maxValue = dp[0];

        for (int i = 1; i < size; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 1], nums[i]);

            maxValue = Math.max(maxValue, dp[i]);
        }

        return maxValue;
    }
}
