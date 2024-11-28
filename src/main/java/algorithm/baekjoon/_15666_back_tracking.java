package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// https://www.acmicpc.net/problem/15666
// 고민 : 중복을 어떻게 제거하는가
public class _15666_back_tracking {

    static int n, m;
    static List<Integer> arrays;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        ans = new int[m];

        String[] lists = br.readLine().split(" ");
        arrays = Arrays.stream(lists).mapToInt(Integer::parseInt).sorted().boxed().collect(Collectors.toList());

        backTracking(0, 0);
        System.out.println(sb);
    }


    static void backTracking(int start, int depth) {

        // 시간초과로 바로 출력
        if (depth == m) {
            for (int value : ans) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 메서드 안에서 값 할당시 지역변수로 유지됨
        int before = 0;
        for (int idx = start; idx < n; idx++) {
            // 현재 인덱스의 해당하는 값을 할당함
            int now = arrays.get(idx);
            // 이전과 같지 않을 경우 재귀
            if (before != now) {
                before = now;
                ans[depth] = arrays.get(idx);
                backTracking(idx, depth + 1);
            }
        }
    }
}
