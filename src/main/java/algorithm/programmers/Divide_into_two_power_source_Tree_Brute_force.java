package algorithm.programmers;


import java.util.ArrayList;

// https://school.programmers.co.kr/learn/courses/30/lessons/86971
public class Divide_into_two_power_source_Tree_Brute_force {

    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        tree = new ArrayList<>();
        for (int x = 0; x < n + 1; x++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < wires.length; i++) {
            int[] node = wires[i];
            tree.get(node[0]).add(node[1]);
            tree.get(node[1]).add(node[0]);
        }

        for (int i = 0; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];

            visited  = new boolean[n + 1];

            // 간선을 끊는다 == 트리에서 해당 송전탑을 제거한다
            tree.get(a).remove(Integer.valueOf(b));
            tree.get(b).remove(Integer.valueOf(a));

            // 해당 노드와 연결된 송전탑 총 계산
            int cnt = dfs(1);

            // 방문한 송전탑 - (전체 - 방문한 송전탑)
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);

            // dfs 완료 후 다시 원복
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        System.out.println(answer);
        return answer;
    }

    static int dfs(int idx) {
        visited[idx] = true;
        int cnt = 1;

        // 방문한 노드만큼 카운트 증가
        for (int next : tree.get(idx)) {
            if (!visited[next]) {
                cnt += dfs(next);
            }
        }
        return  cnt;
    }

    public static void main(String[] args) {
        Divide_into_two_power_source_Tree_Brute_force ds = new Divide_into_two_power_source_Tree_Brute_force();
        int n = 9;
        int[][] inputs = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        ds.solution(n, inputs);
    }
}
