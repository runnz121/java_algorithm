package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 접근법
 * 1. dp 배열 만들고 이중 for 문 돌면서 큰 값 갱신
 * 2. 이중 for 문에서 기준 값 기준으로 현재 값이 더 작아야 해당 값 앞에 붙일 수 있음으로 갱신
 * 예를 들면 기준값이 30 이고 현재값이 10이면 더 작음으로 앞에 이어 붙일 수 있다. -> dp 값 갱신
 */
public class _11053_lcs {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {

            // 초기값은 자기 자신만 선택
            dp[i] = 1;

            for (int j = 0; j < i; j++) {

                // i 기준으로 j가 더 작아야 i 앞에올 수 있다
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
