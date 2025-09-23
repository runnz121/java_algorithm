package algorithm.baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 접근법
 * - 하루마다 전역 visited로 아직 배정되지 않은 칸에서 BFS를 돌며 연합(연결 컴포넌트) 을 만든다.
 * - 연합 내 인구 합/개수로 평균 floor(sum/size) 를 구해 해당 연합 칸들에만 적용한다(임시 맵 → 실제 맵 반영).
 * - 그 날 연합 크기 ≥ 2가 하나도 없으면 종료, 있었다면 날짜 +1 하고 반복.
 */
public class _16234_bfs {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static int[][] map;

    // 이번 라운드(하루) 동안 연합별 평균을 적용할 좌표에 기록할 임시 맵
    // 0이면 변경 없음, 0이 아니면 해당 평균값으로 치환
    static int[][] renewalMap;

    static int N;
    static int L;
    static int R;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {

            // 라운드 시작: 임시 적용 맵 초기화(0 = 미적용)
            renewalMap = new int[N][N];

            // 연합 탐색 및 평균 계산
            checkOpen();

            // renewalMap에 기록된 평균값을 실제 map에 반영
            changeRenewal();

            // 이번 라운드에 하나라도 이동(연합 크기 ≥ 2)이 있었는지 확인
            boolean isContinue = checkContinue();

            if (isContinue == false) {
                break;
            }
            answer += 1;
        }

        System.out.println(answer);
    }

    // 이번 라운드에 이동이 있었는지 확인
    static boolean checkContinue() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (renewalMap[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // renewalMap에 기록된 평균값을 실제 map에 반영
    static void changeRenewal() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (renewalMap[i][j] != 0) {
                    map[i][j] = renewalMap[i][j];
                }
            }
        }
    }

    // 아직 방문하지 않은 칸에서 BFS로 연합을 구성하고,
    // 연합 크기 ≥ 2이면 해당 연합 칸들에 적용할 평균을 renewalMap에 기록
    static void checkOpen() {

        // 라운드 전역 방문 배열
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j]) {
                    continue;
                }

                // 연합에 속한 좌표들 저장
                List<int[]> union = new ArrayList<>();

                int[] res = bfs(i, j, visited, union);
                int clan = res[0];
                int sum = res[1];

                if (clan >= 2) {
                    int avg = (int) Math.floor((double) sum / clan);
                    for (int[] p : union) {
                        renewalMap[p[0]][p[1]] = avg;
                    }
                }
            }
        }
    }

    static int[] bfs(int y,
                     int x,
                     boolean[][] visited,
                     List<int[]> union) {

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x});

        int sum = map[y][x];
        int clan = 1;

        visited[y][x] = true;
        union.add(new int[]{y, x});

        while (que.isEmpty() == false) {
            int[] current = que.poll();
            int cy = current[0];
            int cx = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }

                if (visited[ny][nx]) {
                    continue;
                }

                int abs = Math.abs(map[ny][nx] - map[cy][cx]);
                if (abs < L || abs > R) {
                    continue;
                }

                visited[ny][nx] = true;
                que.add(new int[]{ny, nx});
                union.add(new int[]{ny, nx});
                sum += map[ny][nx];
                clan += 1;
            }
        }

        return new int[]{clan, sum};
    }
}
