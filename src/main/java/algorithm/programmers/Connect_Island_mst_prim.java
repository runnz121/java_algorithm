package algorithm.programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 특징 : 트리를 단계적으로 확장
 *
 * 1. 정점 가중치 기준으로 우선순위 큐 생성
 * 2. 큐에서 꺼낸 후 현재 노드 기준으로 연결되어있는 타 노드 중 가중치가 적은걸 선택
 * 3. 다음 노드를 큐에 넣는다
 *
 */
// https://school.programmers.co.kr/learn/courses/30/lessons/42861
public class Connect_Island_mst_prim {

    static class Point {

        int node;
        int cost;

        Point(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static List<List<Point>> lists = new ArrayList<>();
    static boolean[] visited;

    public int solution(int n, int[][] costs) {

        int ans = 0;
        for (int i = 0; i < n; i++) {
            lists.add(new ArrayList<>());
        }

        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            lists.get(from).add(new Point(to, cost));
            lists.get(to).add(new Point(from, cost));
        }

        visited = new boolean[n];

        // 가중치 기준 우선순위 큐
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparing(o -> o.cost));
        pq.add(new Point(0, 0));

        while (pq.isEmpty() == false) {

            Point current = pq.poll();

            // 이미 방문한 노드인지 확인
            if (visited[current.node]) continue;

            // 방문 처리
            visited[current.node] = true;

            // 가중치 갱신
            ans += current.cost;

            // 현재 노드에서 관련된 정점 모두 확인
            for (int i = 0; i < lists.get(current.node).size(); i++) {
                Point nextPoint = lists.get(current.node).get(i);

                int next = nextPoint.node;
                int cost = nextPoint.cost;

                // 다음 노드가 이미 방문한 노드인지 확인
                if (visited[next]) continue;

                pq.add(new Point(next, cost));
            }
        }

        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        Connect_Island_mst_prim csg =  new Connect_Island_mst_prim();
        int[][] costs = {
                {0,1,1},
                {0,2,2},
                {1,2,5},
                {1,3,1},
                {2,3,8}
        };
        csg.solution(4, costs);
    }
}
