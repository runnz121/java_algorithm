package algorithm.programmers;


import java.util.Arrays;

public class Integer_triangle_dp {

    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        int [][] dp = new int[height][height];

        dp[0][0] = triangle[0][0];
        // 맨 왼쪽은 미리 계산
        for (int i = 1; i < height; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
        }

        // 왼쪽, 오른족 분기 처리
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 1; j < triangle[i].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i][j], dp[i - 1][j] + triangle[i][j]);
            }
        }

        for (int i = 0; i < triangle[height - 1].length; i++) {
            answer = Math.max(dp[height - 1][i], answer);
        }

        return answer;
    }

    public static void main(String[] args) {
        Integer_triangle_dp itd = new Integer_triangle_dp();
        int[][] input = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        itd.solution(input);
    }
}
