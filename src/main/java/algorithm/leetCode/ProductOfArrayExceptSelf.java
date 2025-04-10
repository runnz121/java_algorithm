package algorithm.leetCode;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {

        int size = nums.length;

        int[] answer = new int[size];

        answer[0] = 1;
        for (int i = 1 ; i < size; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int x = size - 1; x >= 0; x--) {
            answer[x] = answer[x] * right;
            right *= nums[x];
        }

        return answer;
    }

    public static void main(String[] args) {

        ProductOfArrayExceptSelf poaes = new ProductOfArrayExceptSelf();

        int[] nums = new int[]{1, 2, 3, 4};
        int[] nums1 = new int[]{-1,1,0,-3,3};

        int[] ints = poaes.productExceptSelf(nums);

        System.out.println(Arrays.toString(ints));
    }
}
