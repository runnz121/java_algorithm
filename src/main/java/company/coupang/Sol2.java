package company.coupang;

public class Sol2 {

	public int solution(int n) {

		int[] dp = new int[n + 1];
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			int maxHeight = 0;
			for (int j = 1; j <= i; j++) {
				int height = Math.min(i-j, j);
				maxHeight = Math.max(maxHeight, height);
			}
			dp[i] = maxHeight * i;
		}

		return dp[n];
	}

	public static void main(String[] args) {
		Sol2 s2 = new Sol2();
		int n = 10; // 나무 상자 개수
		int result = s2.solution(n);
		System.out.println(result);
	}
}
