package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _24447 {

    static int N;
    static int M;
    static int R;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] depth;
    static int[] order;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];
        depth = new int[N + 1];
        order = new int[N + 1];
        Arrays.fill(depth, -1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        graph.forEach(Collections::sort);

        bfs();

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            if (depth[i] >= 0) {
                answer += (long) depth[i] * order[i];
            }
        }

        System.out.println(answer);
    }

    static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(R);
        visited[R] = true;
        depth[R] = 0;
        order[R] = ++count;

        while (que.isEmpty() == false) {
            int node = que.poll();

            for (int v : graph.get(node)) {
                if (!visited[v]) {
                    visited[v] = true;
                    // 깊이
                    depth[v] = depth[node] + 1;
                    // 방문 순서
                    order[v] = ++count;
                    que.add(v);
                }
            }
        }
    }
}
