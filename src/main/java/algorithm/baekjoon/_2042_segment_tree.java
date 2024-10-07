package algorithm.baekjoon;

import java.util.*;
import java.io.*;

public class _2042_segment_tree {

    static int N;
    static int M;
    static int K;
    static long[] arrayA;
    static long[] tree;

    // 트리 초기화
    // tree[node] = start - end 까지 범위의 합
    static void initTree(long[] arrayA,
                         long[] tree,
                         int node,
                         int start,
                         int end) {
        // 리프노드인 경우
        if (start == end) {
            tree[node] = arrayA[start];
        } else {
            initTree(arrayA, tree, node * 2, start, (start + end) / 2);
            initTree(arrayA, tree, node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    // 특정 인덱스의 값을 주어진 value 로 바꿈
    static void update(long[] arrayA,
                       long[] tree,
                       int node,
                       int start,
                       int end,
                       int idx,
                       long value) {
        // idx 가 start, end 를 벗어나는 경우
        if (idx < start || idx > end) {
            return;
        }
        if (start == end) {
            arrayA[idx] = value;
            tree[node] = value;
            return;
        }
        update(arrayA, tree, node * 2, start, (start + end) / 2, idx, value);
        update(arrayA, tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    // left 와 right의 합을 구함
    static long query(long[] tree,
                      int node,
                      int start,
                      int end,
                      int left,
                      int right) {
        // left 와 right 가 start, end 범위를 벗어나는 경우 -> 탐색 종료
        if (left > end || right < start) {
            return 0;
        }
        // left 와 right 가 start, end 범위내에 완전히 포함되는 경우 -> node의 자식이 모두 포함됨 -> 탐색 종료
        if (left <= start && end <= right) {
            return tree[node];
        }
        long leftSum = query(tree, node * 2, start, (start + end) / 2, left, right);
        long rightSum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return leftSum + rightSum;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        M += K;

        arrayA = new long[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            arrayA[i] = Long.parseLong(st1.nextToken());
        }

        // 트리 크기 초기화
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        // 2 ^(h + 1)
        int treeSize = (1 << (h + 1));
        tree = new long[treeSize];

        // 트리 초기화
        initTree(arrayA, tree, 1, 0, N - 1);

        while (M -- > 0) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st2.nextToken());

            // idx -> value 로 바꿈
            if (flag == 1) {
                int idx = Integer.parseInt(st2.nextToken());
                long value = Long.parseLong(st2.nextToken());
                update(arrayA, tree, 1, 0, N - 1, idx - 1, value);
            }
            // left -> right 까지의 합을 구함
            if (flag == 2) {
                int left = Integer.parseInt(st2.nextToken());
                int right = Integer.parseInt(st2.nextToken());
                long sums = query(tree, 1, 0, N - 1, left - 1, right - 1);
                bw.write(sums + "\n");
            }
        }
        bw.flush();
    }
}
