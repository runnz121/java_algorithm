package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 접근법
 * 1. 이전 칸과 크기가 같은경우 +,-1 인 차이이인 경우로 구분
 * 1-1. 내리막길 : 지금 위치 +1 기준 앞으로 L 칸이 바닥이 되어야 함
 * 1-2. 오르막길 : 지금위치 i 기준 뒤로 L 칸이 바닥이 되어야함
 *
 * 2.used -> 이미 경사로가 놓여진 칸 표시
 *
 */
public class _14890_implement {

    static int N;
    static int L;
    static int[][] MAP;
    static int answer = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        MAP = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                MAP[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 행 검사
        for (int y = 0; y < N; y++) {
            int[] line = new int[N];
            for (int x = 0; x < N; x++) {
                line[x] = MAP[y][x];
            }
            if (isGo(line)) {
                answer++;
            }
        }

        // 열 검사
        for (int x = 0; x < N; x++) {
            int[] line = new int[N];
            for (int y = 0; y < N; y++) {
                line[y] = MAP[y][x];
            }
            if (isGo(line)) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static boolean isGo(int[] arr) {

        boolean[] used = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int delta = arr[i + 1] - arr[i];

            // 높이가 같은 경우 -> 통과
            if (delta == 0) {
                continue;
            }

            // 오르막길
            if (delta == 1) {
                int h = arr[i];

                // 범위 밖인 경우
                if (i - (L - 1) < 0) {
                    return false;
                }

                // 경사로는 평평한 곳에 놓아져야 함으로 모든 칸의 높이가 같아야함 + used 가 하나라도 true 면 경사로 못놓음
                for (int j = i; j > i - L; j--) {
                    if (arr[j] != h || used[j]) {
                        return false;
                    }
                }

                // 사용 처리
                for (int j = i; j > i - L; j--) {
                    used[j] = true;
                }
            }

            // 내리막길
            else if (delta == -1) {

                int h = arr[i + 1];

                // 범위 밖인 경우
                if (i + L >= N) {
                    return false;
                }

                // 경사로는 평평한 곳에 놓아져야 함으로 모든 칸의 높이가 같아야함 + used 가 하나라도 true 면 경사로 못놓음
                for (int j = i + 1; j <= i + L; j++) {
                    if (arr[j] != h || used[j]) {
                        return false;
                    }
                }

                // 사용 처리
                for (int j = i + 1; j <= i + L; j++) {
                    used[j] = true;
                }
            }

            // 1초과면 불가
            else {
                return false;
            }
        }
        return true;
    }
}
