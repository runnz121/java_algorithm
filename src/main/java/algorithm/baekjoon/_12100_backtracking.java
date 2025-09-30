package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 접근법
 * - 백트랙킹 기준
 * - 4방향 조건을 어떻게 줄지
 * - 맵 복사를 위한 깊은 복사 진행
 *
 */
public class _12100_backtracking {

    static int N;
    static int[][] maps;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTracking(0);
        System.out.println(answer);
    }

    // 깊이 5까지 모든 경우 탐색 (전역 maps 사용)
    static void backTracking(int depth) {
        if (depth == 5) {
            findMax();
            return;
        }

        // 현재 보드 백업
        int[][] backup = new int[N][N];
        for (int i = 0; i < N; i++) {
            backup[i] = maps[i].clone();
        }

        // 4방향으로 이동 시도
        for (int dir = 0; dir < 4; dir++) {
            move(dir);
            backTracking(depth + 1);

            // 원상복구 (다른 방향 시도를 위함)
            for (int i = 0; i < N; i++) {
                maps[i] = backup[i].clone();
            }
        }
    }

    // dir: 0=위, 1=아래, 2=왼쪽, 3=오른쪽
    static void move(int dir) {
        switch (dir) {
            // 위로 몰기 (각 열 고정, 위쪽부터 스캔)
            case 0:
                for (int c = 0; c < N; c++) {
                    int index = 0;
                    int block = 0;
                    for (int r = 0; r < N; r++) {
                        if (maps[r][c] == 0) {
                            continue;
                        }
                        if (block == maps[r][c]) {
                            maps[index - 1][c] = block * 2;
                            block = 0;
                            maps[r][c] = 0;
                        } else {
                            block = maps[r][c];
                            maps[r][c] = 0;
                            maps[index][c] = block;
                            index++;
                        }
                    }
                }
                break;

            // 아래로 몰기 (각 열 고정, 아래쪽부터 스캔)
            case 1:
                for (int c = 0; c < N; c++) {
                    int index = N - 1;
                    int block = 0;
                    for (int r = N - 1; r >= 0; r--) {
                        if (maps[r][c] == 0) {
                            continue;
                        }
                        // 직전 놓인 값과 같으면 합치기
                        if (block == maps[r][c]) {
                            maps[index + 1][c] = block * 2;
                            block = 0;
                            maps[r][c] = 0;
                        } else {
                            block = maps[r][c];
                            maps[r][c] = 0;
                            maps[index][c] = block;
                            index--;
                        }
                    }
                }
                break;

            // 왼쪽으로 몰기 (각 행 고정, 왼쪽부터 스캔)
            case 2:
                for (int r = 0; r < N; r++) {
                    int index = 0;
                    int block = 0;
                    for (int c = 0; c < N; c++) {
                        if (maps[r][c] == 0) {
                            continue;
                        }
                        if (block == maps[r][c]) {
                            maps[r][index - 1] = block * 2;
                            block = 0;
                            maps[r][c] = 0;
                        } else {
                            block = maps[r][c];
                            maps[r][c] = 0;
                            maps[r][index] = block;
                            index++;
                        }
                    }
                }
                break;

            // 오른쪽으로 몰기 (각 행 고정, 오른쪽부터 스캔)
            case 3:
                for (int r = 0; r < N; r++) {
                    int index = N - 1;
                    int block = 0;
                    for (int c = N - 1; c >= 0; c--) {
                        if (maps[r][c] == 0) {
                            continue;
                        }
                        if (block == maps[r][c]) {
                            maps[r][index + 1] = block * 2;
                            block = 0;
                            maps[r][c] = 0;
                        } else {
                            block = maps[r][c];
                            maps[r][c] = 0;
                            maps[r][index] = block;
                            index--;
                        }
                    }
                }
                break;
        }
    }

    static void findMax() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                answer = Math.max(answer, maps[i][j]);
    }
}
