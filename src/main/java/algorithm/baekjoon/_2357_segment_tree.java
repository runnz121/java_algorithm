package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2357_segment_tree {

    static int N;
    static int M;
    static long[] array;
    static long[] minTree;
    static long[] maxTree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            array[i] = Long.parseLong(st.nextToken());
        }

        // 트리 높이 계산
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
//        int treeSize = (1 << (h + 1));

        minTree = new long[treeSize];
        maxTree = new long[treeSize];

        // 최소 값 저장 트리 초기화
        initMinTree(1, 0, N - 1);
        // 최대 값 저장 트리 초기화
        initMaxTree(1, 0, N - 1);

        // 여기서부터 입력받은 값 바로 출력
        for (int x = 0; x < M; x++) {
            st = new StringTokenizer(br.readLine());

            // 인덱스 맞추기 위해 -1 해줌
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            long min = query(minTree, 1, 0, N - 1, a, b, -1);
            long max = query(maxTree, 1, 0, N - 1, a, b, 1);

            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void initMinTree(int node,
                            int start,
                            int end) {
        if (start == end) {
            minTree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            initMinTree(node * 2, start, mid);
            initMinTree(node * 2 + 1, mid + 1, end);
            // 각 노드에 최소 값을 저장하도록 수정
            minTree[node] = Math.min(minTree[node * 2],  minTree[node * 2 + 1]);
        }
    }

    static void initMaxTree(int node,
                            int start,
                            int end) {
        if (start == end) {
            maxTree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            initMaxTree(node * 2, start, mid);
            initMaxTree(node * 2 + 1, mid + 1, end);
            // 각 노드에 최대 값을 저장하도록 수정
            maxTree[node] = Math.max(maxTree[node * 2],  maxTree[node * 2 + 1]);
        }
    }

    static long query(long[] tree,
                      int node,
                      int start,
                      int end,
                      int left,
                      int right,
                      int flag) {
        // left, right 가 start, end 범위를 벗어나는 경우
        if (left > end || right < start) {
            // 최대값 조회시 -> 최소값 반환
            if (flag > 0) return Long.MIN_VALUE;
                // 최소값 조회시 -> 최대값 반환
            else return Long.MAX_VALUE;
        }
        // left, right 가 start, end 범위를 완전히 포함하는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }
        long leftV = query(tree, node * 2, start, (start + end) / 2, left, right, flag);
        long rightV = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right, flag);

        // flag 에 따라 최소값, 최대값 반환
        if (flag > 0) return Math.max(leftV, rightV);
        else return Math.min(leftV, rightV);
    }
}

