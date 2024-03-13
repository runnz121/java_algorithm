package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 양방한 간선 조건이 아니라서, 체크 배열도, 서로 그래프에 추가해줄 필요가 없다 24481 참고
 *
 */
public class _25516_tree_bfs_dfs {

    static int N;
    static int K;
    static ArrayList<ArrayList<Integer>> tree;
    static int answer;
    static ArrayList<String> nodesList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();

        for (int x = 0; x < N + 1; x++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st1.nextToken());
            int v = Integer.parseInt(st1.nextToken());
            tree.get(u).add(v);
        }
        String sequence = br.readLine();
        String[] nodes = sequence.split(" ");

        nodesList = new ArrayList<>(Arrays.asList(nodes));

        dfs(0, 0);

        System.out.println(answer);
    }

    // 0 3 5
    private static int dfs(int r, int cnt) {
        // 주어진 제한 조건보다 길면 종료
        if (cnt == K + 1) {
            return 0;
        }
        // 기존 입력받은 노드리스트 인덱스의 존재하는 사과값을 더해줌
        answer += Integer.parseInt(nodesList.get(r));
        // 해당 정점이랑 연결되어있는 가지 간선들을 dfs 하면서 조건에 맞으면 더함
        for (int next : tree.get(r)) {
            dfs(next, cnt + 1);
        }
        return answer;
    }
}
