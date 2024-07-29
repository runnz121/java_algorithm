package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/12971

// 처음 스티커를 때고 안때고를 기준으로 분기하여 dp를 처리
// dp 변수를 공유함으로 최대값을 재 비교하여 반환
public class Collect_sticker_DP {

    public int solution(int sticker[]) {
        int answer = 0;
        int[] dp = new int[sticker.length];

        if (sticker.length == 1) return sticker[0];

        // 첫번째 걸 땔 경우
        dp[0] = sticker[0];
        dp[1] = dp[0];
        for (int i = 2; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
        answer = dp[sticker.length - 2];

        // 첫번째가 안땐 경우
        dp[0] = 0;
        dp[1] = sticker[1];
        for (int i = 2; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }
        answer = Math.max(answer, dp[sticker.length - 1]);

        return answer;
    }

    public static void main(String[] args) {
        Collect_sticker_DP cs = new Collect_sticker_DP();
        int[] inputs = {14, 6, 5, 11, 3, 9, 2, 10};
        cs.solution(inputs);
    }
}
