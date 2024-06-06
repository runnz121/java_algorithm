package oHousePay;

import java.util.LinkedList;
import java.util.Queue;

public class Sol2 {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;
    static int answer;
    boolean [][] visited;

    public int solution(String[][] arr) {

        N = arr.length;
        M = arr[0].length;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j].equals("B")) {
                    bfs(new Position(i, j), arr);
                    answer ++;
                }
            }
        }
        System.out.println(answer);
        return answer;
    }

    private void bfs(Position position, String[][] arr) {

        Queue<Position> que = new LinkedList<>();
        que.offer(position);

        while(!que.isEmpty()) {
            Position pop = que.poll();
            int a = pop.x;
            int b = pop.y;
            visited[a][b] = true;

            for (int k = 0; k < 4; k++) {
                int nx = a + dx[k];
                int ny = b + dy[k];

                while (true) {
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && !arr[nx][ny].equals("B")) {
                        nx += dx[k];
                        ny += dy[k];
                    } else {
                        nx -= dx[k];
                        ny -= dy[k];
                        break;
                    }
                }

                if (arr[nx][ny].equals("B") && !visited[nx][ny]) {
                    answer --;
                    break;
                } else if (visited[nx][ny] == false && !arr[nx][ny].equals("B")) {
                    visited[nx][ny] = true;
                    Position update = new Position(nx, ny);
                    que.offer(update);
                } else {

                }
            }
        }
    }

    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Sol2 sol2 = new Sol2();
        String[][] input = {{"W","W","B"},{"W","B","W"},{"B","W","w"}};
        String[][] inputs2 = {{"B","B","W"},{"B","B","W"},{"B","B","W"}};
        sol2.solution(inputs2);
    }
}
