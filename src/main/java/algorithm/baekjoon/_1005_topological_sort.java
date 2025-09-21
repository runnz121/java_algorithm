package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1005_topological_sort {

    /**
     * 문제 접근법
     * - 건물 관계를 유향 그래프 + 진입차수로 설정
     * - 선행 건물이 없는 노드부터 (in-degree = 0) 부터 위상 정렬을 시작
     * - dp[i] = i 번 건물 완공 시각
     *
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            // 건물별 건설 시간
            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            // 그래프 및 in-degree 초기화
            List<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            int[] indegree = new int[N + 1];

            // 선행 조건 간선 읽기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken()); // 선행 건물
                int Y = Integer.parseInt(st.nextToken()); // 후행 건물
                graph[X].add(Y); // X를 지은 뒤 Y를 지을 수 있다 (방향 고정)
                indegree[Y]++; // 진입차수 ( = 해당 노드로 들어오는 간선 수 == 선행 갯수 건물)
            }

            // 목표 건물
            int W = Integer.parseInt(br.readLine());

            // dp[i] = i번 건물을 완료하는 최소 시간
            int[] dp = new int[N + 1];
            Queue<Integer> que = new LinkedList<>();

            // in-degree 0인 건물부터 시작 (착공이 바로가능) (=in-degree 는 어떤 건물을 짓기전 먼저 지어야 하는 선행 갯수)
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    dp[i] = time[i];
                    que.offer(i);
                }
            }

            // 위상정렬 + DP
            while (que.isEmpty() == false) {
                int u = que.poll();

                for (int v : graph[u]) {
                    // v의 최소 완공시간은 (u의 완공시간 + v의 건설시간)
                    // 여러 선행 건물 중 가장 오래 걸린 경로를 고려해야 하므로 max 사용
                    dp[v] = Math.max(dp[v], dp[u] + time[v]);

                    // v의 모든 선행이 처리되면 큐에 넣음
                    if (--indegree[v] == 0) {
                        que.offer(v);
                    }
                }
            }

            sb.append(dp[W]).append('\n');
        }

        System.out.print(sb);
    }
}
