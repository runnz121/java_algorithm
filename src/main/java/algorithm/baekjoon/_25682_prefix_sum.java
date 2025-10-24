package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 접근법
 * 1. 체스판은 ‘왼쪽 위가 W’인 경우와 ‘왼쪽 위가 B’인 두 패턴만 존재한다.
 * 2 각 칸이 패턴과 다르면 1, 같으면 0으로 표시한 두 개의 불일치 배열을 만든다.
 * 3. 불일치 배열에 대해 2차원 누적합(prefix sum)을 계산한다.
 * 4. 모든 K×K 구간의 합을 누적합으로 O(1)에 구한다.
 * 5. 두 패턴 중 더 적은 칸을 칠해야 하는 값을 구해 전체 최소값을 출력한다.
 */
public class _25682_prefix_sum {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[][] map = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String s = br.readLine().trim();
            for (int j = 1; j <= m; j++) {
                map[i][j] = s.charAt(j - 1);
            }
        }

        // 누적합 배열 생성
        int[][] prefixMap = new int[n  + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                // 맨 왼쪽 위가 W 라고 가정
                boolean flag = (i + j) % 2 == 0;
                // 해당 패턴으로 기대되는 flag 값 선택
                char expected = flag ? 'W' : 'B';
                // 현재칸이 기대 칸과 가으면 0 다르면 1
                int mismatch = (map[i][j] == expected) ? 0 : 1;

                prefixMap[i][j] =
                        mismatch // 현재 칸에 0 / 1 더한 값
                        + prefixMap[i - 1][j]  //위쪽 누적
                        + prefixMap[i][j - 1]  // 왼쪽 누적
                        - prefixMap[i - 1][j - 1]; // 두번 더해지는 부분 배제
            }
        }

        int ans = Integer.MAX_VALUE;
        int area = k * k;

        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                // K * K 구간이 (i, j) ~ (i - k, j - k) 합을 구함
                int sumW =
                        prefixMap[i][j] // 전체 영역
                      - prefixMap[i - k][j] // 위쪽 부분 제거
                      - prefixMap[i][j - k] // 왼쪽 부분 제거
                      + prefixMap[i - k][j - k]; // 두 번 빠진 교차부분 복원

                int rePaint = Math.min(sumW, area - sumW); // 왼쪽 위가 W 로 시작하는 경우 (불일치 개수 = sumW) | 왼쪽 위가 B로 시작하는 경우 (area - sumW)

                // 최소 값 갱신
                if (rePaint < ans) {
                    ans = rePaint;
                }
            }
        }

        System.out.println(ans);
    }
}
