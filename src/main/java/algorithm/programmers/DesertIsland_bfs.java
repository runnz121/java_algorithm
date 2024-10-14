package algorithm.programmers;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/154540
public class DesertIsland_bfs {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;
    static char[][] mapArray;
    static boolean[][] visited;
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        N = maps.length;
        M = maps[0].length();

        mapArray = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            mapArray[i] = maps[i].toCharArray();
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (mapArray[x][y] != 'X' && visited[x][y] == false) {
                    int res = bfs(x, y);
                    answer.add(res);
                }
            }
        }

        if (answer.size() == 0) {
            answer.add(-1);
        }

        Collections.sort(answer);
        int[] array = answer.stream().mapToInt(Integer::intValue).toArray();
        return array;
    }

    private int bfs(int x, int y) {
        Queue<Cur> que = new LinkedList<>();
        Cur cur = new Cur(x, y);
        que.offer(cur);

        int total = Integer.parseInt(String.valueOf(mapArray[x][y]));
        visited[x][y] = true;

        while (!que.isEmpty()) {

            Cur out = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = out.x + dx[i];
                int ny = out.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (visited[nx][ny] == false && mapArray[nx][ny] != 'X') {
                        visited[nx][ny] = true;
                        total += Integer.parseInt(String.valueOf(mapArray[nx][ny]));
                        Cur newCur = new Cur(nx, ny);
                        que.offer(newCur);
                    }
                }
            }
        }
        return total;
    }

    static class Cur {
        int x;
        int y;

        Cur(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        DesertIsland_bfs dib = new DesertIsland_bfs();
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        dib.solution(maps);
        String[] maps1 = {"XXX","XXX","XXX"};
        dib.solution(maps1);
    }
}
