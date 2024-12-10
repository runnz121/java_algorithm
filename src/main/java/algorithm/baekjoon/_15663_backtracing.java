package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _15663_backtracing {

    static int N;
    static int M;
    static int[] lists;
    static boolean[] visited;
    static LinkedHashSet<String> result = new LinkedHashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String[] s = br.readLine().split(" ");

        lists = new int[N];
        for (int i = 0; i < N; i++) {
            lists[i] = Integer.parseInt(s[i]);
        }

        Arrays.sort(lists);

        visited = new boolean[lists.length];

        backTracking(0, new ArrayList<>());

        for (String sequence : result) {
            System.out.println(sequence);
        }
    }

    static void backTracking(int depth, List<Integer> ans) {
        if (depth == M) {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < ans.size(); i++) {
                if (i != 0) out.append(" ");
                out.append(ans.get(i));
            }
            result.add(out.toString());
            return;
        }


        for (int i = 0; i < lists.length; i++) {
            if (!visited[i]) {

                if (i > 0 && lists[i] == lists[i - 1] && !visited[i - 1]) continue;

                visited[i] = true;
                ans.add(lists[i]);

                backTracking(depth + 1, ans);

                ans.remove(ans.size() - 1);
                visited[i] = false;
            }
        }
    }
}
