package algorithm.leetCode;

import java.util.*;

public class ThreeSums {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        // 0번째부터 (n-3)번째까지 순회
        for (int i = 0; i < nums.length - 2; i++) {

            // 이미 처리한 값과 중복되는 숫자는 건너뜁니다.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            // left와 right 포인터를 사용하여 합 구하기
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 찾은 해를 결과에 추가
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 중복된 값을 건너뛰기 위해 좌우 포인터 조정
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    // 합이 음수이면 left 포인터를 증가시켜 합을 크게 만듦
                    left++;
                } else {
                    // 합이 양수이면 right 포인터를 감소시켜 합을 작게 만듦
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        ThreeSums ts = new ThreeSums();
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = ts.threeSum(nums);
        System.out.println(lists);
        // [-1, -1, 0, 1, 2, -1]
        // [-1, 0, 0, 2, 1, -1]
    }
}
