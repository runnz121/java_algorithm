package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _24481_dfs {

    static int N;
    static int M;
    static int R;
    static ArrayList<ArrayList<Integer>> graph;
    static int [] visit;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()) - 1;

        // 그래프를 위한 2차원 배열 생성
        graph = new ArrayList<>();
        // 방문 배열 생성
        visit = new int[N];

        for (int x = 0; x <N; x++) {
            graph.add(new ArrayList<>());
            // - 1로 초기화
            visit[x] = -1;
        }
        // 각 정점의 서로 연관관계 설정
        for (int i = 0; i < M; i++) {

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken()) - 1;
            int v = Integer.parseInt(st2.nextToken()) - 1;

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // 오름 차순 정렬
        graph.forEach(Collections::sort);
        // 시작 간선 = 0
        visit[R] = 0;
        // dfs
        dfs(R);

        Arrays.stream(visit).forEach(System.out::println);
    }

    private static void dfs(int r) {
        for (int i = 0; i < graph.get(r).size(); i++) {
            if (visit[graph.get(r).get(i)] == -1) {
                visit[graph.get(r).get(i)] = visit[r] + 1;
                dfs(visit[graph.get(r).get(i)]);
            }
        }
    }
}
