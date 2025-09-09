package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _4179_bfs {

    static int[] dy = {0,0,-1,1};
    static int[] dx = {-1,1,0,0};
    static int N;
    static int M;
    static char[][] map;
    static int[][] fire;
    static int[][] person;

    /**
     * 문제 접근법
     *
     * 1. 두개의 bfs를 진행한다 -> 큐가 2개
     * 2. 불을 먼저 bfs 돌린다
     * 3. 불 bfs 진행 후 지훈 bfs 돌릴시 불보다 먼저 도착한 케이스만 갱신
     * 4. 지훈의 경우 다음의 탈출 조건을 고려해야함
     *      1. 각 가장자리에 도착 -> 탈출 + 1
     *      2. 범위 바깥 -> 탈출 + 1
     */
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        fire = new int[N][M];
        person = new int[N][M];

        for (int k = 0; k < N; k++) {
            Arrays.fill(fire[k], -1);
            Arrays.fill(person[k], -1);
        }

        Queue<int[]> fq = new LinkedList<>();
        Queue<int[]> pq = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = charArray[j];

                if (charArray[j] == 'J') {
                    pq.add(new int[]{i, j});
                    person[i][j] = 0;
                }

                // 불 여러개 존재할 수 있음
                else if (charArray[j] == 'F') {
                    fq.add(new int[]{i, j});
                    fire[i][j] = 0;
                }
            }
        }

        // 불 bfs
        while (fq.isEmpty() == false) {

            int[] current = fq.poll();

            int fy = current[0];
            int fx = current[1];

            for (int i = 0; i < 4; i++) {
                int newY = fy + dy[i];
                int newX = fx + dx[i];

                // 범위 바깥
                if (newY < 0 || newX < 0 || newY >= N || newX >= M) {
                    continue;
                }

                // 벽은 제외
                if (map[newY][newX] == '#') {
                    continue;
                }

                // 이미 갱신됨
                if (fire[newY][newX] != -1) {
                    continue;
                }

                // 불 시간 갱신
                fq.add(new int[]{newY, newX});
                fire[newY][newX] = fire[fy][fx] + 1;
            }
        }

        // 지훈 bfs
        while (pq.isEmpty() == false) {

            int[] current = pq.poll();

            int py = current[0];
            int px = current[1];

            // 가장 자리면 탈출
            if (py == 0 || px == 0 || py == N -1 || px == M - 1) {
                System.out.println(person[py][px] + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newY = py + dy[i];
                int newX = px + dx[i];

                // 범위 바깥 -> 이때는 다음에 탈출한다는 뜻
                if (newY < 0 || newX < 0 || newY >= N || newX >= M) {
                    System.out.println(person[py][px] + 1);
                    return;
                }

                // 벽은 제외
                if (map[newY][newX] == '#') {
                    continue;
                }

                // 이미 갱신됨
                if (person[newY][newX] != -1) {
                    continue;
                }

                // 불이 더 빠른 경우 -> 지훈은 통과 못함
                int next = person[py][px] + 1;
                if (fire[newY][newX] != -1 && fire[newY][newX] <= next) {
                    continue;
                }

                person[newY][newX] = next;
                pq.add(new int[]{newY, newX});
            }
        }

        // 위 조건 없으면 탈출 못함
        System.out.println("IMPOSSIBLE");
    }
}
