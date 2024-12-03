package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1197_mst {

    static int N;
    static int M;
    static int[] parent;
    static long answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가중치 최소 중량 기준으로 정렬
        Queue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.weight));

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            pq.add(new Node(node1,node2, weight));
        }

        parent = new int[N + 1];
        // 부모 노드 초기화
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i;
        }

        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Node poll = pq.poll();
            int parent1 = poll.parent;
            int child1 = poll.child;
            if (find(parent1) != find(child1)) {
                answer += poll.weight;
                union(parent1, child1);
            }
        }

        System.out.println(answer);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    static class Node {

        int parent;
        int child;
        long weight;

        Node(int parent, int child, long weight) {
            this.parent = parent;
            this.child = child;
            this.weight = weight;
        }
    }
}
