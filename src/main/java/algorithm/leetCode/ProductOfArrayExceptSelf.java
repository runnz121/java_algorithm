package algorithm.leetCode;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        int[] answers = new int[nums.length];

        // left
        answers[0] = 1;
        for (int left = 1; left < nums.length; left++) {
            answers[left] = answers[left - 1] * nums[left - 1];
        }

        // right
        int rightx = 1;
        for (int right = nums.length - 1; right >= 0; right--) {
            answers[right] = answers[right] * rightx;
            rightx *= nums[right];
        }

        return answers;
    }

    public static void main(String[] args) {

        ProductOfArrayExceptSelf poaes = new ProductOfArrayExceptSelf();

        int[] nums = new int[]{1, 2, 3, 4};
        int[] nums1 = new int[]{-1,1,0,-3,3};

        int[] ints = poaes.productExceptSelf(nums);

        System.out.println(Arrays.toString(ints));
    }
}
