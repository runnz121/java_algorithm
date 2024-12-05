package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _4485_dijkstra {

    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n;

    static class Node {
        int x;
        int y;
        int weight;

        Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String sn = null;
        int idx = 1;
        while ((sn = br.readLine()).equals("0") == false) {
            n = Integer.parseInt(sn);
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            int cost = dijkstra();
            sb.append("Problem " + idx + ": " + cost+"\n");
            idx += 1;
        }
        System.out.println(sb.toString());
    }

    static int dijkstra() {
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        que.add(new Node(0, 0, map[0][0]));

        while (que.isEmpty() == false) {
            Node current = que.poll();
            int px = current.x;
            int py = current.y;
            int w = current.weight;

            // 최종 목적지에 도달한 경우 종료
            if (px == n - 1 && py == n - 1) return w;

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue;

                // 가중치 기준으로 갱신 (더 작은 값으로)
                if (w + map[ny][nx] < dist[ny][nx]) {
                    dist[ny][nx] = w + map[ny][nx];
                    que.add(new Node(nx, ny, dist[ny][nx]));
                }
            }
        }
        return -1;
    }
}
