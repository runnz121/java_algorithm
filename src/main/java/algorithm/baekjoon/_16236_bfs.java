package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16236_bfs {
    static int N;
    static int currentSize;
    static int currentAteFish;
    static int curY, curX;
    static int time;
    static int[][] graph;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Fish> eatableFishes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        currentSize = 2;
        time = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st2.nextToken());
                if (graph[i][j] == 9)  {
                    curY = i;
                    curX = j;
                }
            }
        }

        while (true) {
            // 먹을 수 있는 물고기가 있는지 확인
            if (eatableFishes.isEmpty()) {
                break;
            };

            // 먹으러 갈 수 있는 물고기들 위치를 반환
            List<Fish> nearestFish = findNearestFish(eatableFishes);

            while (true) {

                
            }


            // 위의 물고기들 중 아무것도 도달 할 수 없으면 종료

            // 해당 물고기를 먹으러 갈 수 있는지 확인

            // 먹으러 갈 수 있다면 상어 위치를 이동
            checkMoveAble();


        }

        bw.write(time);
        bw.flush();
    }

    static boolean checkMoveAble(Fish fish) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {fish.x, fish.y});

        while (!que.isEmpty()) {

            int[] poll = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1) continue;

                if (nx == fish.x && ny == fish.y) return true;

                // 현재 사이즈보다 큰 먹이는 지나갈 수 없다.
                if (graph[ny][nx] > currentSize) continue;

                que.add(new int[] {nx, ny});
            }
        }
        return false;
    }

    // 먹을 수 있는 물고기 있는지 확인 및 물고기 탐색
    static void checkEatableFish(int[][] graph) {
        // 무조건 초기화
        eatableFishes = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int target = graph[y][x];
                if (target != 0 && target != 9 && target < currentSize) {
                    int posDiff = Math.abs(y - curY) + Math.abs(x - curX);
                    eatableFishes.add(new Fish(y, x, target, posDiff));
                }
            }
        }
    }

    static List<Fish> findNearestFish(List<Fish> eatableFishes) {

        eatableFishes.sort((f1, f2) -> {
            // 1. 거리가 가까운 순서대로 정렬
            if (f1.posDiff != f2.posDiff) {
                return Integer.compare(f1.posDiff, f2.posDiff);
            }
            // 2. 가장 위에 있는 물고기 (y가 작을 수록)
            if (f1.y != f2.y) {
                return Integer.compare(f1.y, f2.y);
            }
            // 3. 가장 왼쪽에 있는 물고기 (x가 작을 수록)
            return Integer.compare(f1.x, f2.x);
        });

        return eatableFishes;
    }

    static class Fish {
        int y;
        int x;
        int size;
        int posDiff;

        Fish(int y, int x, int size, int posDiff) {
            this.y = y;
            this.x = x;
            this.size = size;
            this.posDiff = posDiff;
        }
    }
}
