package algorithm.leetCode;

/**
 * 투 포인터 기준으로
 *
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {

        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {

            // 넓이
            int x = right - left;

            // 더 낮은 높이 기준으로 계싼된다 -- 1.
            int h = Math.min(height[left], height[right]);

            // 현재 넓이 비교해서 더 큰 걸로 갱신
            maxArea= Math.max(maxArea, h * x);

            // 투 포인터 기준으로 낮은쪽을 이동시킴 -> 넓이 계산은 더 낮은 높이 기준으로 계산됨(..1 참고)으로 폭은 줄어들지만 높이가 높아질 가능성이 있음
            if (height[left] < height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater cmw = new ContainerWithMostWater();

        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height2 = new int[]{1, 1};
        int[] height3 = new int[]{1, 2, 1};
        int[] height4 = new int[]{0, 2};
        int[] height5 = new int[]{2, 0};
        int i1 = cmw.maxArea(height);
        System.out.println(i1);
    }
}
