package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// https://www.acmicpc.net/problem/16236
public class _16236_bfs {
    static int N;
    static int currentSize;
    static int currentAteFish;
    static int curY, curX;
    static int time;
    static int[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
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
                    // 상어 현재 포지션
                    curY = i;
                    curX = j;
                    // 상어 포지션 초기화
                    graph[i][j] = 0;
                }
            }
        }

        while (true) {
            // 먹을 수 있는 물고기 초기화
            findEatableFish(graph);

            // 먹을 수 있는 물고기가 있는지 확인
            if (eatableFishes.isEmpty()) break;

            // 먹으러 갈 수 있는 물고기 위치를 반환
            Fish nearestFish = findNearestFish(eatableFishes);

            // 위의 물고기들 중 아무것도 도달 할 수 없으면 종료
            if (Objects.isNull(nearestFish)) break;

            // 해당 물고기로 상어 위치 및 사이즈, 먹은 갯수 갱신
            currentAteFish += 1;
            curY = nearestFish.y;
            curX = nearestFish.x;
            time += nearestFish.posDiff;

            // 해당 물고기 위치 변경
            graph[nearestFish.y][nearestFish.x] = 0;

            // 현재 먹은 물고기와 사이즈 같다면 사이즈 증가 및 먹은 물고기 갯수 초기화
            if (currentAteFish == currentSize) {
                currentAteFish = 0;
                currentSize += 1;
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
        bw.close();
    }

    // 먹을 수 있는 물고기 있는지 확인 및 물고기 탐색
    static void findEatableFish(int[][] graph) {
        // 무조건 초기화
        eatableFishes = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int target = graph[y][x];
                // 물이 아니고 상어 아니고 현재보다 크기가 작은 값 들을 넣음
                if (target != 0 && target != 9 && target < currentSize) {
                    int posDiff = getDistByBfs(curY, curX, y, x);
                    if (posDiff != Integer.MAX_VALUE) {
                        eatableFishes.add(new Fish(y, x, target, posDiff));
                    }
                }
            }
        }
    }

    // bfs로 상어와 먹이의 거리 반환
    static int getDistByBfs(int startY, int startX, int targetY, int targetX) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[] {startY, startX, 0});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0], x = current[1], dist = current[2];

            // 먹이와 위치에 도달시 거리 반환
            if (y == targetY && x == targetX) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nx < 0 || ny < 0 || nx > N - 1 || ny > N - 1 || visited[ny][nx])  continue;

                // 현재 사이즈보다 큰 먹이는 지나갈 수 없다.
                if (graph[ny][nx] > currentSize) continue;

                visited[ny][nx] = true;
                // 거리 + 1 증가
                queue.add(new int[] {ny, nx, dist + 1});
            }
        }
        return Integer.MAX_VALUE;
    }

    static Fish findNearestFish(List<Fish> eatableFishes) {

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

        // 첫번째 우선순위 물고기 반환
        return eatableFishes.get(0);
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
