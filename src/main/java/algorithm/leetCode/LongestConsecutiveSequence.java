package algorithm.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutiveSequence {
	public int longestConsecutive(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}

		Set<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toSet());
		int[] array = collect.stream().mapToInt(Integer::intValue).toArray();

		List<Integer> minus = new ArrayList<>();
		List<Integer> plus = new ArrayList<>();

		for (int i = 0; i < collect.size(); i++) {

			if (array[i] < 0) {
				minus.add(array[i]);
			} else {
				plus.add(array[i]);
			}
		}

		Collections.sort(minus);
		Collections.sort(plus);

		List<Integer> total = new ArrayList<>();
		total.addAll(minus);
		total.addAll(plus);

		int ans = 0;
		int subAns = 1;
		for (int i = 0; i < total.size() - 1; i++) {
			if (total.get(i + 1) == total.get(i) + 1) {
				subAns += 1;
			} else {
				ans = Math.max(subAns, ans);
				subAns = 1;
			}
		}

		ans = Math.max(ans, subAns);

		return ans;
	}

	public static void main(String[] args) {

		LongestConsecutiveSequence ls = new LongestConsecutiveSequence();

		int[] nums = new int[]{4, 0, -1, 1, -2, 2, -3};

		int i = ls.longestConsecutive(nums);

		System.out.println(i);
	}
}
