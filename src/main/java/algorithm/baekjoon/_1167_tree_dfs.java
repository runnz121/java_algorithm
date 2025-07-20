package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 이슈 1.
 * 최초 풀이 코드 -> 시간초과 -> N 최대 100_000 일때 통과 불가
 * -> 해결
 * -> 임의의 정점에서 제일 먼 정점을 찾는다 (정점 A)
 * -> 해당 정점에서 탐색하여 제일 먼 거리를 구한다.
 * -> 재귀는 최대 트리 높이만큼 수행
 *
 * 비슷한 문제 -> 1967
 *
 */
public class _1167_tree_dfs {

    static int V;
    static List<List<Node>> tree = new ArrayList<>();

    static int farthestNode;
    static int maxDist;

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

        // 가장 먼 정점 찾기
        maxDist = 0;
        boolean[] visited = new boolean[N + 1];
        dfs(1, 0, visited);
        int theFarthestNode = farthestNode;

        // 가장 먼 정점 기준으로 재귀돌려서 최대거리 찾기
        maxDist = 0;
        boolean[] visited2 = new boolean[N + 1];
        dfs(theFarthestNode, 0, visited2);

        System.out.println(maxDist);
    }

    static void dfs(int start,
                    int len,
                    boolean[] visited) {

        visited[start] = true;

        // 최대 정점 거리 찾는 부분
        if (len > maxDist) {
            maxDist = len;
            farthestNode = start;
        }

        // 노드 탐색 부분
        List<Node> nodes = tree.get(start);
        for (int k = 0; k < nodes.size(); k++) {

            Node node = nodes.get(k);
            if (visited[node.node] == false) {
                int newLen = len + node.length;
                visited[node.node] = true;
                dfs(node.node, newLen, visited );
            }
        }
    }
}
