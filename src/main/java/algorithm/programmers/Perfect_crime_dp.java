package algorithm.programmers;

import java.util.Arrays;

/**
 * 문제 기본 설명 -> 물건 1를 A가 훔치면 B는 못훔침
 *
 * 해결 -> dp 배열을 동적으로 만들기 위해 2개를 생성 -> for 문 끝날때 갱신
 * dp 배열에서 A, B 가 모두 훔치는 것이 가능한 조건에서 가장 작은 A 값을 찾아서 반환
 *
 */
public class Perfect_crime_dp {

    public int solution(int[][] info, int n, int m) {
        int answer = -1;
        int length = info.length;

        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int i = 0; i < length; i++) {

            // 물건 결과 저장 dp 배열 생성
            boolean[][] nextDp = new boolean[n][m];

            for (int a = 0; a < n; a++) {
                for (int b = 0; b < m; b++) {

                    // 둘다 false 면 둘다 못훔치는 것
                    if (dp[a][b] == false) {
                        continue;
                    }

                    // a가 i를 훔치는 경우
                    int nexA = a + info[i][0];
                    int nexB = b;
                    if (nexA < n) {
                        nextDp[nexA][nexB] = true;
                    }

                    // b가 i를 훔치는 경우
                    nexA = a;
                    nexB = b + info[i][1];
                    if (nexB < m) {
                        nextDp[nexA][nexB] = true;
                    }
                }
            }
            // dp 갱신
            dp = nextDp;
        }

        System.out.println(Arrays.deepToString(dp));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j]) {
                    // 문제 조건에서 A 최소 값 기준임으로 i 기준으로 확인
                    answer = i;
                    break;
                }
            }
            // 최소값 보장 위해 중간에 종료
            if (answer != -1) {
                break;
            }
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Perfect_crime_dp pcd = new Perfect_crime_dp();

        int[][] info = {{1, 2}, {2, 3}, {2, 1}};
        pcd.solution(info, 4, 4);

        int[][] info1 = {{1, 2}, {2, 3}, {2, 1}};
        pcd.solution(info1, 1, 7);

        int[][] info2 = {{3, 3}, {3, 3}};
        pcd.solution(info2, 7, 1);

        int[][] info3 = {{3, 3}, {3, 3}};
        pcd.solution(info3, 6, 1);
    }
}
