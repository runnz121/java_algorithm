package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _14428_segment_tree {

    static int N;
    static int[] array;
    static int M;
    static int[] tree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new int[treeSize];

        initTree(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

//            if (flag == 2) {);

//            sb.append().append("\n");
        }
        System.out.println(sb.toString());
    }

    static void initTree(int node,
                         int start,
                         int end) {
        if (start == end) {
            tree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            initTree(node * 2, start, mid);
            initTree(node * 2 + 1, mid + 1, end);
            tree[node] = Math.min(tree[node * 2], tree[node + 1]);
        }
    }

    static long query(int node,
                      int start,
                      int end,
                      int left,
                      int right) {

        // 범위를 완전히 벗어나는 경우
        if (left > end || right < start) {
            return 0;
        }

        // 범위 완전 포함인 경우
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        long leftSum = query(node * 2, start, mid, left, right);
        long rightSum = query(node * 2 + 1, mid + 1, end, left, right);
        return leftSum + rightSum;
    }

    static void update(int node,
                       int start,
                       int end,
                       int idx,
                       int value) {
        if (idx < start || idx > end) {
            return;
        }
        if (start == end) {
            array[idx] = value;
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, value);
        update(node * 2 + 1, mid + 1, end, idx, value);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }
}
