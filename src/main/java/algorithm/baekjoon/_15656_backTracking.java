package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _15656_backTracking {

    static int N;
    static int M;
    static int [] result;
    static StringBuilder sb = new StringBuilder();
    static List<Integer> lists = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];
        String[] inputs = br.readLine().split(" ");

        for (int idx = 0; idx < inputs.length; idx++) {
            lists.add(Integer.valueOf(inputs[idx]));
        }
        Collections.sort(lists);
        List<Integer> printList = new ArrayList<>();

        backTracing(printList, lists, 0);
        System.out.println(sb);
    }

    private static void backTracing(List<Integer> printList,
                                    List<Integer> lists,
                                    int depth) {

        if (depth == M) {
            for (int out = 0; out < M;  out++) {
                sb.append(lists.get(result[out]));
                if (!(out == printList.size() - 1)) sb.append(" ");
            }
            sb.append("\n");
            return;
        } else {
            for (int p = 0; p < lists.size(); p++) {
                result[depth] = p;
                backTracing(printList, lists, depth + 1);
            }
        }
    }
}
