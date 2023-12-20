package algorithm.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {

    public int trap(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int vol = 0;
        int leftMax = height[left];
        int rightMax = height[right];

        // 투포인터가 겹칠 떄까지 반복
        while (left < right) {

            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            // 오른쪽이 더 크면
            if (leftMax <= rightMax) {
                vol = vol + (leftMax - height[left]);
                left += 1;
            } else {
                vol = vol + (rightMax - height[right]);
                right -= 1;
            }
        }
        return vol;
    }

    public int trapStack(int[] height) {

        Deque<Integer> stack = new ArrayDeque<>();
        int vol = 0;

        for (int i = 0; i < height.length; i++) {
            // 스택이 비어있지 않고, 변곡점을 만날 경우
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {

                Integer top = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }
                // 스택 마지막 위치까지 거리 계산
                int distance = i - stack.peek() - 1;
                // 현재 높이 혹은 스택의 마지막 위치 높이 중 낮은 값에 방금 거낸 높이의 차이를 물 높이로 지정
                int waters = Math.min(height[i], height[stack.peek()]) - height[top];
                // 물이 쌓이는 양은 거리 곱하기 물 높이
                vol += distance * waters;
            }
            stack.push(i);
        }
        return vol;
    }

    public static void main(String[] args) {
        TrappingRainWater tp = new TrappingRainWater();
        int [] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int result = tp.trap(height);
        System.out.println(result);
        int result2 = tp.trapStack(height);
        System.out.println(result2);
    }
}
