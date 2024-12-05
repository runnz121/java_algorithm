package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _1753_dijkstra {

    static int V;
    static int E;
    static int START;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;

    static class Node {
        int idx;
        int weight;

        Node (int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());

        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        graph = new ArrayList<>();
        for (int j = 0; j < V + 1; j++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(v, w));
        }

        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        que.add(new Node(START, 0));
        dist[START] = 0;

        while (que.isEmpty() == false) {
            Node current = que.poll();
            int idx = current.idx;
            int w = current.weight;

            if (dist[current.idx] < w) continue;

            for (int i = 0; i < graph.get(idx).size(); i++) {
                Node next = graph.get(idx).get(i);
                int newWeight = dist[current.idx] + next.weight;
                if (newWeight < dist[next.idx]) {
                    dist[next.idx] = newWeight;
                    que.add(new Node(next.idx, newWeight));
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }
}
