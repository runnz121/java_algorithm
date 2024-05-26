package algorithm.programmers;


public class Expression_of_numbers {

    static int[] dp;
    static int answer = 0;

    public int solution(int n) {
        dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }

        for (int x = 1; x <= n; x++) {
            if (!(x % 2 == 0)) {
                recursive(x, n);
            }
        }
        System.out.println(answer);
        return answer;
    }

    static void recursive(int startIdx, int end) {
        int sum = 0;
        for (int k = startIdx; k <= end; k++) {
            sum += dp[k];
            if (sum == end) {
                answer += 1;
                break;
            }
            // 누적합이 최대치보다 큰 경우 바로 종료 (효율성)
            if (sum > end) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Expression_of_numbers ex = new Expression_of_numbers();
        ex.solution(7);
    }
}
