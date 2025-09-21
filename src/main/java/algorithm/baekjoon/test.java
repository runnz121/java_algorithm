package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class test {

    static List<List<Node>> nodes;

    public static class Node {

        int idx;
        int weight;

        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] items = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        nodes = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes.get(a).add(new Node(b, c));
            nodes.get(b).add(new Node(a, c));
        }

        int answer = 0;
        for (int s = 1; s <= N; s++) {

            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(x -> x[1]));
            pq.offer(new int[]{s, 0});

            while (pq.isEmpty() == false) {

                int[] cur = pq.poll();
                int idx = cur[0];
                int wg = cur[1];

                if (dist[idx] < wg) {
                    continue;
                }

                for (Node node : nodes.get(idx)) {

                    int v = node.idx;
                    int newWeight = node.weight + wg;

                    if (newWeight < dist[v]) {
                        dist[v] = newWeight;
                        pq.offer(new int[]{v, newWeight});
                    }
                }
            }

            int sum = 0;
            for (int i = 1; i <= N; i++) {

                if (dist[i] <= M) {
                    sum += items[i];
                }
                answer = Math.max(answer, sum);
            }
        }
        System.out.println(answer);
    }
}


