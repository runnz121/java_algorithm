package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 접근법
 * - 각 노드의 아이템 수가 정해짐
 * - 양방향 간선 조건과 길이 조건이 있음
 * - 특정 지역엣서의 수색범위 M 이하로 도달 가능한 모든 아이템 합을 구해야함
 * - 가능한 모든 시작 지역 계산 (최대 아이템 수 출력)
 *
 */
public class _14938 {

    static List<List<Node>> nodes;

    static class Node {

        int start;
        int weight;

        public Node(int start, int weight) {
            this.start = start;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 아이템 정보 입력
        int[] items = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // 노드 초기화
        nodes = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < R; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 입력시 주의 a 인덱스일 경우 b 인덱스로 받아야함
            Node node1 = new Node(b, c);
            Node node2 = new Node(a, c);

            nodes.get(a).add(node1);
            nodes.get(b).add(node2);
        }

        int answer = 0;
        for (int s = 1; s <= N; s++) {

            int[] dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;

            // 정점과 현재까지의 거리 저장
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[]{s, 0});

            while (pq.isEmpty() == false) {

                int[] cur = pq.poll();
                int u = cur[0];
                int d = cur[1];

                if (d > dist[u]) {
                    continue;
                }

                for (Node node : nodes.get(u)) {
                    int v = node.start;
                    int newWeight = node.weight + d;

                    // 더 짧은 경로를 발견한 경우 거리 갱신
                    if (newWeight < dist[v]) {
                        dist[v] = newWeight;
                        pq.offer(new int[]{v, newWeight});
                    }
                }
            }

            // 경로기준으로 조사 범위 이내의 정점에서의 아이템 추출해서 누적
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
