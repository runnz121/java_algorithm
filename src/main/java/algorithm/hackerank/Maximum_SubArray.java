package algorithm.hackerank;

import java.util.Collections;
import java.util.List;

// https://www.hackerrank.com/challenges/maxsubarray/problem
public class Maximum_SubArray {

    public static List<Integer> maxSubarray(List<Integer> arr) {

        int arrSize = arr.size();
        int[] accumulateArr = new int[arrSize];

        // 누적합 구하기
        accumulateArr[0] = arr.get(0);
        for (int i = 1; i < arrSize; i++) {
            accumulateArr[i] = accumulateArr[i - 1] + arr.get(i);
        }

        // prefix[i] - minPrefix 가 최대 부분 배열 합이 될 수 있도록
        // 지금까지 등장한 prefix 합의 최솟값을 저장할 변수
        int minPrefix = 0;
        int maxSubarraySum = Integer.MIN_VALUE;

        for (int i = 0; i < arrSize; i++) {

            // i 번재에서 지금까지의 최소 누적합 값을 뺴본다
            maxSubarraySum = Math.max(maxSubarraySum, accumulateArr[i] - minPrefix);

            // 최소 누적합 정보를 갱신
            minPrefix = Math.min(minPrefix, accumulateArr[i]);
        }

        // 리스트에서 가장 큰 원소를 구함
        // 모든 원소가 ≤ 0일 때 최대 비연속 합은 이 값이 됨
        int maxElem = Collections.max(arr);
        int sumPos = 0;

        // 양수만 더하기
        for (int x : arr) {
            if (x > 0) sumPos += x;
        }

        // 양수가 존재하는 경우, 아닌 경우를 바탕으로 답을 반환
        int maxSubsequenceSum = (sumPos > 0 ? sumPos : maxElem);

        return List.of(maxSubarraySum, maxSubsequenceSum);

    }

    public static void main(String[] args) {
        List<Integer> integers = Maximum_SubArray.maxSubarray(List.of(2, -1, 2, 3, 4, -5));
        System.out.println(integers);
    }
}
