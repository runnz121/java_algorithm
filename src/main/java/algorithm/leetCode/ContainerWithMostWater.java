package algorithm.leetCode;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {

            // 1. 폭 계산
            int width = right - left;

            // 2. 높이(min) 계산
            int h = Math.min(height[left], height[right]);

            // 3. 면적 계산·갱신
            maxArea = Math.max(maxArea, h * width);

            // 4 “더 낮은 쪽” 포인터를 안쪽으로 한 칸 이동 -> 높은쪽을 줄이면 넓이는 작아짐으로 작은쪽을 줄여서 갱신한 최대값을 반환
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
