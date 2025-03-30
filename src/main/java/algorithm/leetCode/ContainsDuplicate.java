package algorithm.leetCode;

import java.util.*;
public class ContainsDuplicate {

	public boolean containsDuplicate(int[] nums) {

		Map<Integer, Integer> countMap = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			countMap.put(nums[i], countMap.getOrDefault(nums[i] , 0) + 1);
		}

		for (Map.Entry<Integer, Integer> map : countMap.entrySet()) {
			if (map.getValue() >= 2) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {

	}
}
