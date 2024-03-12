package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _24481_dfs_graph {

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
        R = Integer.parseInt(st.nextToken());

        // 그래프를 위한 2차원 배열 생성
        graph = new ArrayList<>();
        // 방문 배열 생성
        visit = new int[N + 1];

        for (int x = 0; x <N + 1; x++) {
            // 기본 그래프 초기화
            graph.add(new ArrayList<>());
            // - 1로 초기화
            visit[x] = -1;
        }
        // 각 정점의 서로 연관관계 설정
        for (int i = 0; i < M; i++) {

            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // 오름 차순 정렬
        graph.forEach(Collections::sort);
        // 시작 간선 = 0
        visit[R] = 0;
        // dfs (시작 정점 대입)
        dfs(R);
        // 방문 배열 출력
        Arrays.stream(visit).skip(1).forEach(System.out::println);
    }

    private static void dfs(int r) {
        for (int i = 0; i < graph.get(r).size(); i++) {
            // 노드의 깊이가 == -1 이면 아직 방문 하지 않음
            if (visit[graph.get(r).get(i)] == -1) {
                // 방문 상태가 아니라면, 노드 깊이 갱신 -> 이전노드 깊이 + 1
                visit[graph.get(r).get(i)] = visit[r] + 1;
                // dfs 재귀 호출
                dfs(graph.get(r).get(i));
            }
        }
    }
}
