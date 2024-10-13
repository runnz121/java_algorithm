package algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/159993
public class Escape_maze_bfs {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;

    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();

        char[][] mapArray = new char[N][M];

        for (int i = 0; i < N; i++) {
            mapArray[i] = maps[i].toCharArray();
        }

        int[] start = null, lever = null, exit = null;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (mapArray[r][c] == 'S') {
                    start = new int[]{r, c};
                } else if (mapArray[r][c] == 'L') {
                    lever = new int[]{r, c};
                } else if (mapArray[r][c] == 'E') {
                    exit = new int[]{r, c};
                }
            }
        }

        int toLever = bfs(mapArray, start, lever);
        if (toLever == -1) {
            return -1; // 레버에 도달할 수 없으면 탈출 불가
        }

        // 레버에서 출구까지의 최단 경로
        int toExit = bfs(mapArray, lever, exit);
        if (toExit == -1) {
            return -1; // 출구에 도달할 수 없으면 탈출 불가
        }

        System.out.println(toLever + toExit);
        return toLever + toExit;
    }

    private int bfs(char [][] maps, int [] start, int [] target) {
        Queue<Cur> q = new LinkedList<>();
        Cur cur = new Cur(start[0], start[1]);
        q.offer(cur);
        boolean [][] visited = new boolean[N][M];
        visited[start[0]][start[1]] = true;
        int dist = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            for (int x = 0; x < size; x++) {
                Cur out = q.poll();
                if (out.x == target[0] && out.y == target[1]) {
                    return dist;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = out.x + dx[i];
                    int ny = out.y + dy[i];

                    if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                        if (maps[nx][ny] != 'X' && visited[nx][ny] == false) {
                            visited[nx][ny] = true;
                            Cur newCur = new Cur(nx, ny);
                            q.offer(newCur);
                        }
                    }
                }
            }
            dist++;
        }
        return  -1;
    }

    static class Cur {
        int x;
        int y;

        Cur (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Escape_maze_bfs em = new Escape_maze_bfs();
        String [] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        String [] maps1 = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        em.solution(maps);
//        em.solution(maps1);
    }
}
