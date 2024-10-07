package algorithm.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/159993
public class Escape_maze_bfs {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;
    static int[][] countMap;
    static boolean[][] checkMap;
    static String[][] toMap;

    public int solution(String[] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length();

        countMap = new int[maps.length][maps[0].length()];
        checkMap = new boolean[maps.length][maps[0].length()];
        toMap = new String[maps.length][maps[0].length()];

        initMap(maps);
        // 시작지점 -> 레버
        for (int i = 0; i < maps.length; i++) {
            String[] toMapSplit = maps[i].split("");
            for  (int j = 0; j < maps[i].length(); j++) {
                toMap[i][j] = toMapSplit[j];
                if (toMapSplit[j].equals("S")) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(Arrays.deepToString(countMap));

        // 레버 -> 출구
//        initMap(maps);
//        for (int i = 0; i < maps.length; i++) {
//            String[] toMapSplit = maps[i].split("");
//            for  (int j = 0; j < maps[i].length(); j++) {
//                toMap[i][j] = toMapSplit[j];
//                if (toMapSplit[j].equals("S")) {
//                    bfs(i, j, false);
//                }
//            }
//        }

        return answer;
    }

    private void initMap(String[] maps) {
        for (int i = 0; i < maps.length; i++) {
            String[] toMapSplit = maps[i].split("");
            for  (int j = 0; j < maps[i].length(); j++) {
                toMap[i][j] = toMapSplit[j];
                if (toMapSplit[j].equals("X")) {
                    countMap[i][j] = -1;
                    checkMap[i][j] = true;
                }
            }
        }
    }

    private int countDestination(boolean isStart) {

    }

    private void bfs(int x, int y) {
        Queue<Cur> q = new LinkedList<>();
        Cur cur = new Cur(x, y);
        q.offer(cur);

        while (!q.isEmpty()) {
            Cur out = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = out.x + dx[i];
                int ny = out.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if ((toMap[nx][ny].equals("O") || toMap[nx][ny].equals("L") || toMap[nx][ny].equals("E"))&& checkMap[nx][ny] == false) {
                        countMap[nx][ny] = countMap[out.x][out.y] + 1;
                        checkMap[nx][ny] = true;
                        Cur newCur = new Cur(nx, ny);
                        q.offer(newCur);
                    }
                }
            }
        }
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
        em.solution(maps);
    }
}
