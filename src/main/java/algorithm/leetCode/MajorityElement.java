package algorithm.leetCode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// 분활정복
public class MajorityElement {

	public int majorityElement1(int[] nums) {
		Map<Integer, Integer> countMap = new HashMap<>();
		for (int num : nums) {
			countMap.put(num, countMap.getOrDefault(num, 0) + 1);
		}
		// 입력 순서로 저장하기 위해 linked hash map으로 지정
		Map<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();

		countMap.entrySet().stream()
			// hashmap 내부 value를 정렬
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			// 위의 정렬 값을 새로운 linked hasmap으로 저장
			.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

		// 첫 항목 추출
		Map.Entry<Integer, Integer> entry = reverseSortedMap.entrySet().iterator().next();

		// 과반수 넘었을 경우 정답.
		if (entry.getValue() > nums.length / 2) {
			return entry.getKey();
		}

		return -1;
	}

	// divide and conquer
	public int majorityElement2(int[] nums) {
		return divideAndConquer(0, nums.length - 1, nums);
	}

	public int divideAndConquer(int left,
								int right,
								int[] nums) {
		if (left == right) {
			return nums[left];
		}

		int mid = left + (right - left) / 2;
		int a = divideAndConquer(left, mid, nums);
		int b = divideAndConquer(mid + 1, right, nums);

		int countA = 0;

		for (int i = left; i <= right; i++) {
			if (nums[i] == a) {
				countA++;
			}
		}
		// a 의 갯수를 샌 값이 과반수가 넘으면 a 값을 리턴 그렇지 않으면 b를 리턴
		return countA > (right - left + 1) / 2 ? a : b;
	}


	public static void main(String[] args) {
		MajorityElement mj = new MajorityElement();
		int[] sample = {2,2,1,1,1,2,2};
//		int res = mj.majorityElement1(sample);
		int res = mj.majorityElement2(sample);
		System.out.println(res);
	}
}
