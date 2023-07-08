package zum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import algorithm.programmers.ShortestGameMap_bfs;

public class Sol4 {

    static int N;
    static int M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] ARR;
    static int[][] GRID;
    static int curCount;
    static int curSize;
    public int[] solution(int[][] grid) {
        int[] answer = {};
        N = grid.length;
        M = grid[0].length;
        ARR = new int[N][M];
        GRID = grid;
        for (int i = 1; i < 30; i ++) {
            bfs(i*i);
            // 마름모 사이즈마다 갯수 초기화
            ARR = new int[N][M];
            curCount = 0;
        }



        return answer;
    }

    static void bfs(int size) {
        Queue<Cur> q = new LinkedList<Cur>();
        Cur cur = new Cur(0,0);
        q.offer(cur);
        int diamondCount = 0;
        List<Cur> checkCur = new ArrayList<>();

        while(!q.isEmpty()) {
            Cur out = q.poll();
            // 현재 위치가 마름모 사이즈일 경우 확인
            if (GRID[out.x][out.y] == size && ARR[out.x][out.y] != -1) {
                ARR[out.x][out.y] = -1;
                diamondCount += 1;
                // 탐색
                for (int i = 0; i < 4; i++) {
                    int nx = out.x + dx[i];
                    int ny = out.y + dy[i];
                    // 내부일 경우
                    if ((0 <= nx) && (nx < N) && (0 <= ny) && (ny < M)) {
                        // 방문 하지 않았고 마륾모 타겟 사이즈일 경우
                        if (ARR[nx][ny] != -1 && GRID[nx][ny] == size) {
                            Cur newCur = new Cur(nx, ny);
                            ARR[nx][ny] = -1;
                            q.offer(newCur);
                            diamondCount += 1;
                        }
                    }
                }
                // diamnodCount 가 주어진 사이즈와 같을 경우 마름모인지 확인
                if (diamondCount == size) {
                    if (checkDiamond(checkCur)) {

                    }
                }
            }
        }
    }

    // [x][y] 의 값이 각각 n 만큼 존재하는지 확인
    static boolean checkDiamond(List<Cur> checkCurList) {
        checkCurList.sort(new Comparator<Cur>() {
            @Override
            public int compare(Cur o1, Cur o2) {
                if (o1.x < o2.x) {
                    return 1;
                }
                if (o1.y < o2.y) {
                    return 1;
                }
                return 0;
            }
        });
        //
        // checkCurList.stream().forEach(
        //     it -> it.
        // );
        return false;
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
        Sol4 t = new Sol4();
        int[][] grid = {{2,1,1,3,5,1},{1,1,3,3,5,5,},{8,3,3,3,1,5},{3,3,3,4,4,4},{3,3,4,4,4,4},{1,4,4,4,4,4}};
        t.solution(grid);
    }
}
