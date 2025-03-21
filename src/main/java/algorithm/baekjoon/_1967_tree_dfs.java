package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 12
 * 1 2 3
 * 1 3 2
 * 2 4 5
 * 3 5 11
 * 3 6 9
 * 4 7 1
 * 4 8 7
 * 5 9 15
 * 5 10 4
 * 6 11 6
 * 6 12 10
 *
 */
public class _1967_tree_dfs {

    static int N;

    static List<List<Node>> graph = new ArrayList<>();

    static int ANSWER = Integer.MIN_VALUE;

    static int[] DISTANCE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0 ; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 입력
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Integer parent = Integer.parseInt(st.nextToken());
            Integer child = Integer.parseInt(st.nextToken());
            Integer weight = Integer.parseInt(st.nextToken());

            // 양방향 입력
            Node node1 = new Node(child, weight);
            graph.get(parent).add(node1);

            Node node2 = new Node(parent, weight);
            graph.get(child).add(node2);
        }

        DISTANCE = new int[N + 1];
        Arrays.fill(DISTANCE, -1);
        DISTANCE[1] = 0;

        // 1. 루트 -> 가장 먼 노드 탐색
        dfs(1, 0);

        int startNode = findFarthest();
        DISTANCE = new int[N + 1];
        Arrays.fill(DISTANCE, -1);
        DISTANCE[startNode] = 0;

        // 2. 가장 먼 노드 -> 가장 먼 노드 탐색 == 최대로 긴 거리 임으로
        dfs(startNode, 0);

        ANSWER = Arrays.stream(DISTANCE).max().getAsInt();

        bw.write(String.valueOf(ANSWER));
        bw.flush();
        bw.close();
    }

    static void dfs(int node, int oWeight) {
        for (Node n : graph.get(node)) {
            int targetNode = n.node;
            int weight = n.weight;
            if (DISTANCE[targetNode] == -1) {
                DISTANCE[targetNode] = oWeight + weight;
                dfs(targetNode, oWeight + weight);
            }
        }
    }

    static int findFarthest() {
        int maxIdx = 1;
        for (int i = 1; i <= N; i++) {
            if (DISTANCE[i] > DISTANCE[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    static class Node {
        int node;
        int weight;

        Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
