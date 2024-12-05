package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _5972_dijkstra {

    static class Node {
        int idx;
        int weight;

        Node (int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    static int V;
    static int E;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(v, w));
            graph.get(v).add(new Node(s, w));
        }

        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        que.add(new Node(1, 0));

        while (que.isEmpty() == false) {
            Node current = que.poll();

            if (dist[current.idx] < current.weight) continue;

            for (int i = 0; i < graph.get(current.idx).size(); i++) {
                Node next = graph.get(current.idx).get(i);
                int newWeight = next.weight + dist[current.idx];
                if (dist[next.idx] > newWeight) {
                    dist[next.idx] = newWeight;
                    que.add(new Node(next.idx, newWeight));
                }
            }
        }

        System.out.println(dist[V]);
    }
}
