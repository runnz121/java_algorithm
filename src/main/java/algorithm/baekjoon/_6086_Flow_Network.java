package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/6086
public class _6086_Flow_Network {

    static int N;
    static int V = 52;
    // 간선에 따라 흐르는 유량
    static int[][] flow;
    // 각 간선의 용량
    static int[][] capacity;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flow = new int[V][V];
        capacity = new int[V][V];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = atoi(st.nextToken().charAt(0));
            int b = atoi(st.nextToken().charAt(0));
            int cost = Integer.parseInt(st.nextToken());

            capacity[a][b] += cost;
            capacity[b][a] += cost;
        }

        maxFlow(0,25);
    }

    private static void maxFlow(int start,
                                int destination) {

        int totalFlow = 0;
        int[] parent = new int[V];
        Deque<Integer> que = new LinkedList<>();
        while (true) {
            Arrays.fill(parent, -1);

            parent[start] = start;
            que.add(start);

            while (!que.isEmpty() && parent[start] == -1) {
                int current = que.poll();
                for (int next = 0; next < V; next++) {

                    // 잔여 용량 (capaciyt - flow) 이 남아있을 경우 간선 따라 탐색
                    if (capacity[current][next] - flow[current][next] > 0 && parent[next] == -1) {
                        que.add(next);
                        parent[next] = current;
                    }
                }
            }
            // 더 이상 증가 경로가 없으면 종료
            if (parent[destination] == -1) break;

            // 증가 경로를 통해 보낼 유량을 결정
            int amount = Integer.MAX_VALUE;
            for (int i = destination; i != start; i = parent[i]) {
                amount = Math.min(capacity[parent[i]][i] - flow[parent[i]][i], amount);
            }

            // 증가 경로를 통해 유량을 보낸다.
            for (int i = destination; i != start; i = parent[i]) {
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }

            totalFlow += amount;
        }

        System.out.println(totalFlow);
    }

    // 노드 이름이 알파벳 임으로 idx로 변경
    private static int atoi(char c) {
        if ('A' <= c && c <= 'Z') {
            return c - 'A';
        } else if ('a' <= c && c <= 'z') {
            return c -'A'- 6;
        }
        return -1;
    }
}
