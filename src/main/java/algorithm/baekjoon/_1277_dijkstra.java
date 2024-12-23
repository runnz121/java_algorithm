package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class _1277_dijkstra {

    static int N;
    static int W;
    static double M;
    static int[][] map;
    static int[][] dist;

    static class Node {
        int x;
        int y;
        int weight;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        for (int i = 0; i < W; i++) {

        }
    }
}
