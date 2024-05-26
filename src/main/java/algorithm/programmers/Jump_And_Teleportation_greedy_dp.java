package algorithm.programmers;

public class Jump_And_Teleportation_greedy_dp {

    // 거꾸로 접근 (최대치부터)
    public int solution(int n) {
        int ans = 0;

        while (n > 0) {
            // 홀수일 경우 한칸 점프
            if (n % 2 == 1) {
                ans += 1;
            }
            // 그렇지 않을 경우 순간이동
            n /= 2;
        }
        return ans;
    }

    public static void main(String[] args) {

        Jump_And_Teleportation_greedy_dp ts = new Jump_And_Teleportation_greedy_dp();
        ts.solution(5000);
    }
}
