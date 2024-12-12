package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _26996_brute_force {

    static int N;
    static int[][] graph;
    static int answer = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][2];

        int idx = 1;
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            int xx = Integer.parseInt(st.nextToken());
            int yy = Integer.parseInt(st.nextToken());
            int[] target = new int[]{xx, yy};
            graph[idx] = target;
            idx += 1;
        }

        for (int i = 1; i <= N; i++){
            int[] first = graph[i];
            int fx = first[0];
            int fy = first[1];

            for (int j = i + 1; j <= N; j++) {
                int[] second = graph[j];
                int sx = second[0];
                int sy = second[1];

                for (int k = j + 1; k <= N; k++) {
                    int[] third = graph[k];
                    int tx = third[0];
                    int ty = third[1];

                    // 백터 외적으로 판단 -> 다음의 조건이 만족하면 세점이 일직선상에 위치
                    // (x2−x1) ∗ (y3−y1) − (y2−y1) ∗ (x3−x1) = 0
                    if ((sx - fx) * (ty - fy) == (sy - fy) * (tx - fx)) {
                        answer += 1;
                        sb.append(i).append(" ").append(j).append(" ").append(k).append("\n");
                    }
                }
            }
        }

        if (answer == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(answer);
        System.out.println(sb.toString());
    }
}
