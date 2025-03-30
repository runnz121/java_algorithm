package algorithm.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class TopKFrequentElements {

	public int[] topKFrequent(int[] nums, int k) {

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}

		// 아래 부분을 max heap 으로 대체 -> value 기준으로 정렬
		//----------------
		PriorityQueue<Integer> maxPq =new PriorityQueue<>(
			(a, b) -> map.get(b) - map.get(a)
		);

		maxPq.addAll(map.keySet());

		int[] result = new int[k];

		for (int i = 0; i < k; i++) {
			result[i] = maxPq.poll();
		}
		return result;

		//---------------- 기존
		// Map<Integer, Integer> sortedMap = map.entrySet().stream()
		// 	.sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
		// 	.collect(Collectors.toMap(
		// 		Map.Entry::getKey,
		// 		Map.Entry::getValue,
		// 		(oldValue, newValue) -> oldValue,
		// 		LinkedHashMap::new
		// 	));
		//
		//
		// List<Integer> answerList = new ArrayList<>();
		//
		// for (Map.Entry<Integer, Integer> smp : sortedMap.entrySet()) {
		// 	Integer key = smp.getKey();
		// 	answerList.add(key);
		// }
		//
		// return answerList.stream().limit(k).mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		TopKFrequentElements tfk = new TopKFrequentElements();
		int[] p = new int[] {1,1,1,2,2,3};
		int[] ints = tfk.topKFrequent(p, 2);

		System.out.println(Arrays.toString(ints));
	}
}
