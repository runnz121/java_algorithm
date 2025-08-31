package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/6593

/**
 *  예제 입력
 *
 *  3 4 5
 * S....
 * .###.
 * .##..
 * ###.#
 *
 * #####
 * #####
 * ##.##
 * ##...
 *
 * #####
 * #####
 * #.###
 * ####E
 *
 * 1 3 3
 * S##
 * #E#
 * ###
 *
 * 0 0 0
 *
 *
 * 문제 접근 방향
 * - 단순한 BFS 이지만 4방향 탐색 및 상하 개념이 들어가서 실질적으로는 6방향 탐색을 해야함
 * - 상하 개념이 포함되어있음으로 z, y, x 로 3차원 배열을 써야함
 * - 입력 받을시 한줄의 공백이 들어가서 적용됨으로 이 부분을 예외처리
 * - 큐 비었을때 출력을 위한 fllag 기반 설정이 필요하다
 */
public class _6593_bfs {
    // 6 방향 탐색임으로 위아래 개념 추가
    static int[] upDown = {0, 0, 0, 0, 1, -1};
    static int[] DY = {0, 0, 1, -1, 0, 0};
    static int[] DX = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String theLine = br.readLine();

            // 빈 라인이면 컨티뉴
            if (theLine.isEmpty()) {
                continue;
            }

            StringTokenizer st = new StringTokenizer(theLine);

            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 입력값 모두 0이면 종료
            if (L == 0 && R == 0 && C == 0) {
                return; // 종료
            }

            // 3차원 맵
            char[][][] map = new char[L][R][C];
            int[][][] countMap = new int[L][R][C];

            // 카운트 초기화
            for (int z = 0; z < L; z++) {
                for (int r = 0; r < R; r++) {
                    Arrays.fill(countMap[z][r], -1);
                }
            }

            // 값 입력 받기
            for (int z = 0; z < L; z++) {
                for (int r = 0; r < R; r++) {
                    String row = br.readLine();

                    // 층/행 사이에 낀 빈 줄을 만나면 스킵 후 다시 읽기
                    while (row != null && row.isEmpty()) {
                        row = br.readLine();
                    }
                    map[z][r] = row.toCharArray();
                }
            }

//            System.out.println(Arrays.deepToString(map));

            Deque<int[]> que = new ArrayDeque<>();
            // 탈출 실패 표식
            int answer = -1;

            for (int a = 0; a < L; a++) {
                for (int b = 0; b < R; b++) {
                    for (int c = 0; c < C; c++) {
                        if (map[a][b][c] == 'S') {
                            que.add(new int[] {a, b, c});
                            countMap[a][b][c] = 0;
                        }
                    }
                }
            }

            while (que.isEmpty() == false) {

                int[] poll = que.poll();
                int layer = poll[0];
                int y = poll[1];
                int x = poll[2];

                if (map[layer][y][x] == 'E') {
                    answer = countMap[layer][y][x];
                    break;
                }

                // 6방향탐색 (문제 조건에 4방향 탐색은 같은 층에서됨으로 위아래 * 4방향을 동시에 보는것이 아니라 한 타임 기준으로 봐야한다)
                // 예를 들면 한타임을 소비하여 윗층으로 가거나 아랫층으로 가거나, 한 타임에 윛층/아랫층 으로 간 후 동서남북중 한곳으로 가는 개념이 아님
                for (int k = 0; k < 6; k++) {

                    int dLayer = upDown[k] + layer;
                    int dy = DY[k] + y;
                    int dx = DX[k] + x;

                    // 범위 탐색
                    if (dLayer < 0 || dLayer >= L || dy < 0 || dy >= R || dx < 0 || dx >= C) {
                        continue;
                    }

                    // 갈 수 있는 길인지 확인
                    if (map[dLayer][dy][dx] == '#') {
                        continue;
                    }

                    // 방문 여부 확인 및 카운트
                    if (countMap[dLayer][dy][dx] != -1) {
                        continue;
                    }

                    // 카운트 증가 및 큐 삽입
                    que.add(new int[] {dLayer, dy, dx});
                    countMap[dLayer][dy][dx] = countMap[layer][y][x] + 1;
                }
            }

            // 탈출 성공 후 flag 값 기준으로 분기 처리
            if (answer == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.printf("Escaped in %d minute(s).%n", answer);
            }
        }
    }
}
