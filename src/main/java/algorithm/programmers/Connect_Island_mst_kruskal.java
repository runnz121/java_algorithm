package algorithm.programmers;

import java.util.Arrays;
import java.util.Comparator;

// https://school.programmers.co.kr/learn/courses/30/lessons/42861
public class Connect_Island_mst_kruskal {

    static int[] parent;

    public int solution(int n, int[][] costs) {

        int answer = 0;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 가중치 기준 오름차순 정렬
        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < costs.length; i++) {
            if (find(costs[i][0]) != find(costs[i][1])) {
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }
        System.out.println(answer);
        return  answer;
    }

    int find (int a) {
        if (parent[a] == a) return a;
        // 자기가 속해있는 부모 노드를 찾아서 반환한다.
        return parent[a] = find(parent[a]);
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) {
        Connect_Island_mst_kruskal csg =  new Connect_Island_mst_kruskal();
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        csg.solution(4, costs);
    }
}
