package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1277_dijkstra {

    static int N;
    static int W;
    static double M;
    static int[][] map;
    static List<Node> nodes = new ArrayList<>();
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[][] visited;

    static class Node {
        int v;
        int weight;

        Node(int v,
             int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int x = 1; x <= N; x++) {
            graph.add(new ArrayList<>());
        }

        // 발전소 좌표 저장
        double[] x = new double[N + 1];
        double[] y = new double[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        for (int k = 0; k < W; k++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            visited[to][from] = true;
            visited[from][to] = true;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double calDist = getDistance(x[i], y[i], x[j], y[j]);

                if (visited[i][j]) {

                }
            }
        }

        

        System.out.println(graph);
    }

    static double getDistance(double x1,
                              double y1,
                              double x2,
                              double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
