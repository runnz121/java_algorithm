package algorithm.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSums {

	public int[] twoSum(int[] nums, int target) {

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {

			int remain = target - nums[i];

			if (map.containsKey(remain)) {
				return new int[]{map.get(remain), i};
			}

			map.put(nums[i], i);
		}

		return new int[]{0, 0};
	}

	public static void main(String[] args) {

		TwoSums ts = new TwoSums();
		int [] k = new int[]{2, 7, 11, 15};
		int[] ints = ts.twoSum(k, 9);

		System.out.println(Arrays.toString(ints));
	}
}
