package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3197
public class _3197_bfs_disjointSet {

    static int R;
    static int C;
    static char [][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int days;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        days = 0;

        for (int i = 0 ; i < R; i++) {
            String s = br.readLine();
            String[] split = s.split("");
            for (int j = 0; j < C; j++){
                char c = split[j].charAt(0);
                if (c == 'L') {

                }
                graph[i][j] = c;
            }
        }

        System.out.println(Arrays.deepToString(graph));
    }
}
