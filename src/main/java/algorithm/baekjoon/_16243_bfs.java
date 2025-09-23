package algorithm.baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16243_bfs {

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};

    static int[][] map;

    static boolean[][] renewalMap;

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

            renewalMap = new boolean[N][N];

            // 열린 부분 확인
            int toRenewalPoint = checkOpen();

            // 열린 부분 갱신
            changeRenewal(toRenewalPoint);

            // 가능한지 확인
            boolean isContinue = checkContinue();

            if (isContinue == false) {
                break;
            }
            answer += 1;
        }

        System.out.println(answer);
    }

    static boolean checkContinue() {

        int accClan = 0;
        int accSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] res = bfs(i, j, true);
                accClan += res[0];
                accSum += res[1];
            }
        }

        if (accSum == 0 && accClan == 0) {
            return false;
        }

        return true;
    }

    static void changeRenewal(int toRenewalPoint) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (renewalMap[i][j]) {
                    map[i][j] = toRenewalPoint;
                }
            }
        }
    }

    static int checkOpen() {

        int accClan = 0;
        int accSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] res = bfs(i, j, false);
                accClan += res[0];
                accSum += res[1];
            }
        }

        return (int) Math.floor((double) accSum / accClan);
    }

    static int[] bfs(int y, int x, boolean flagCheck) {

        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x});

        int sum = 0;
        int clan = 1;

        boolean[][] visited = new boolean[N][N];
        visited[y][x] = true;

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

                int abs = Math.abs(map[ny][nx] - map[y][x]);
                if (abs < L || abs > R) {
                    continue;
                }

                visited[ny][nx] = true;
                que.add(new int[]{ny, nx});

                if (flagCheck == false) {
                    sum += map[ny][nx];
                    renewalMap[ny][nx] = true;
                }
            }
        }

        return new int[]{clan, sum};
    }
}
