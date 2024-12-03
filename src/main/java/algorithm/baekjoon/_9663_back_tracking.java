package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _9663_back_tracking {

    static int N;
    // 행 (가로)
    static int[] column;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        column = new int[N];

        backTracking(0);

        System.out.println(answer);
    }

    static void backTracking(int depth) {

        if (depth == N) {
            answer += 1;
            return;
        }

        for (int i = 0; i < N; i++) {
            // 해당 행에는 퀸이 무조건 하나만 존재
            column[depth] = i;
            if (possible(depth)) {
                backTracking(depth + 1);
            }
        }
    }

    static boolean possible(int i) {
        // 마지막과 이전것을 비교
        for (int j = 0; j < i; j++) {
            // 놓을 행이 다른 퀸이 있는지 확인
            if (column[i] == column[j]) {
                return false;
            }
            // 대각선 방향에 퀸이 있는지 확인 (기울기)
            // 열의 차이와 행의 차이가 같은 경우 대각선이다
            if (Math.abs(i - j) == Math.abs(column[i] - column[j])) {
                return false;
            }
        }
        return true;
    }
}
