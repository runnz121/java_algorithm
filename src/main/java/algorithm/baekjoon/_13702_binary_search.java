package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _13702_binary_search {

    static int N;
    static int M;
    static List<Integer> alcohols = new ArrayList<>();
    static int max;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            alcohols.add(value);
            max = Math.max(max, value);
        }

        // 1. 정렬
        Collections.sort(alcohols);

        // 2. 초기화
        long left = 1;
        long right = max;

        while (left <= right) {

            long mid = (left + right) / 2;

            int totalCount = 0;
            for (int alcohol : alcohols) {
                totalCount += alcohol / mid;
            }

            if (totalCount >= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }
}
