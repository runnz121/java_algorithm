package algorithm.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/12978?language=java
public class Delivery_dijkstra {

    static ArrayList<ArrayList<Node>> graph;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // 초기화
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int x = 0; x < road.length; x++) {
            int[] info = road[x];
            int to = info[0];
            int from = info[1];
            int cost = info[2];

            graph.get(to).add(new Node(from, cost));
            graph.get(from).add(new Node(to, cost));
        }

        // 최단 시간 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        // 우선순위 큐 사용
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.offer(new Node(1, 0));

        // 큐가 빌때까지 순회
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            // 현재 코스트가 현재 인덱스보다 작다면 이미 최소
            if (dist[curNode.idx] < curNode.cost) continue;

            // 선택된 인덱스의 주변 노드를 모두 탐색
            for (Node nearNode : graph.get(curNode.idx)) {

                // 현재 노드 코스트 + 인근 노드 코스트
                int newCost = nearNode.cost + curNode.cost;

                // 새로운 코스트가 현재 코스트 보다 작다면 갱신
                if (newCost < dist[nearNode.idx]) {
                    dist[nearNode.idx] = newCost;
                    pq.add(new Node(nearNode.idx, newCost));
                }
            }
        }

        // 주어진 K 값보다 작은 것들만 카운트
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                cnt ++;
            }
        }

        answer = cnt;
        System.out.println(answer);
        return answer;
    }

    static class Node {

        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Delivery_dijkstra d = new Delivery_dijkstra();
        int n = 5;
        int k = 3;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        d.solution(n, road, k);
    }
}
