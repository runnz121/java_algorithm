package algorithm.leetCode;

import java.util.Arrays;

public class HouseRobber {

    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            int comp1 = Math.max(nums[i] + dp[i - 2], dp[i] + nums[i - 2]);
            dp[i] = Math.max(comp1, dp[i - 1]);
        }

        int i = Arrays.stream(dp).max().stream().findFirst().orElse(Math.max(dp[0], dp[1]));

        return i;
    }

    public static void main(String[] args) {

        HouseRobber hr = new HouseRobber();

        int [] nums = new int[]{1,3,1,3,100};

        hr.rob(nums);
    }
}
