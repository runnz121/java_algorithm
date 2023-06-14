package algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestGameMap_bfs {

    static int N;
    static int M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer;
    static int[][]MAP;
    static int[][]ARR;

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        ARR = new int[N][M];
        MAP = maps;
        bfs();
        answer = ARR[N-1][M-1];
        if (answer == 0) return -1;
        System.out.println(answer + 1);
        return answer + 1;
    }

    static void bfs() {
        Queue<Cur> q = new LinkedList<>();
        Cur cur = new Cur(0,0);
        q.offer(cur);

        while(!q.isEmpty()) {
            Cur out = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = out.x + dx[i];
                int ny = out.y + dy[i];

                if ((0 <= nx) && (nx < N) && (0 <= ny) && (ny < M)) {
                    if (MAP[nx][ny] == 1) {
                        ARR[nx][ny] = ARR[out.x][out.y] + 1;
                        Cur newCur = new Cur(nx, ny);
                        q.offer(newCur);
                        // 해당 맵은 통과했음으로 -1 로 상태 변환 처리
                        MAP[nx][ny] = -1;
                    }
                }
            }
        }
    }

    static class Cur {
        int x;
        int y;

        Cur(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }

    public static void main(String[] args) {
        ShortestGameMap_bfs t = new ShortestGameMap_bfs();
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        t.solution(maps);
    }

}
