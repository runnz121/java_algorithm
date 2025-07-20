package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 이슈 1.
 * 최초
 *
 *
 */
public class _1167_dfs {

    static int V;
    static List<List<Node>> tree = new ArrayList<>();
    static int answer;

    public static class Node {

        int node;
        int length;

        public Node(int node,
                    int length) {

            this.node = node;
            this.length = length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 전체 노드 수
        int N = Integer.parseInt(br.readLine());
        V = N;

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        while (V > 0) {

            String[] split = br.readLine().split(" ");
            int targetNode = Integer.parseInt(split[0]);
            for (int x = 1; x < split.length - 1; x += 2) {

                int length = Integer.parseInt(split[x + 1]);
                int theNode = Integer.parseInt(split[x]);

                Node target = new Node(theNode, length);
                Node opposit = new Node(targetNode, length);

                tree.get(targetNode).add(target);
                tree.get(theNode).add(opposit);
            }

            V --;
        }

        for (int startNode = 0; startNode < N; startNode++) {

            boolean[] visited = new boolean[N + 1];

            // dfs 로 탐색
            dfs(startNode, 0, visited);

        }

        System.out.println(answer);
    }
    static void dfs(int start,
                    int len,
                    boolean[] visited) {

        visited[start] = true;

        List<Node> nodes = tree.get(start);
        for (int k = 0; k < nodes.size(); k++) {

            Node node = nodes.get(k);
            if (visited[node.node] == false) {
                int newLen = len + node.length;
                visited[node.node] = true;
                answer = Math.max(answer, newLen);
                dfs(node.node, newLen, visited );
            }
        }
    }
}
