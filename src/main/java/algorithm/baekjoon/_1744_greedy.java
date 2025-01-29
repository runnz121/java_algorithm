package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1744_greedy {

    static long sum;
    static int N, ZERO, ONE;
    static List<Long> upper = new ArrayList<>();
    static List<Long> down = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Long ele = Long.parseLong(st.nextToken());
            // 1 보다 큰 것들 -> 1보다 커야 곱해서 값이 커짐
            if (ele > 1) {
                upper.add(ele);
            }
            // 1인 것들 -> 단순 더해야 값이 커짐
            else if (ele == 1) {
                ONE ++;
            }
            // 음수인 것들 -> 서로 곱해야 양수 되어서 값이 커짐
            else if (ele < 0) {
                down.add(ele);
            }
            // 존재시 음수 상쇄용
            else {
                ZERO ++;
            }
        }

        // 양수 내림 차순 정렬
        upper.sort(Comparator.reverseOrder());

        // 음수 오름 차순 정렬
        Collections.sort(down);

        // 양수 조건
        for (int i = 0; i < upper.size(); i += 2) {
            if (i + 1 < upper.size()) {
                sum += upper.get(i) * upper.get(i + 1);
            } else {
                sum += upper.get(i);
            }
        }

        // 음수 조건
        for (int i = 0; i < down.size(); i += 2) {
            if (i + 1 < down.size()) {
                sum += down.get(i) * down.get(i + 1);
            } else {
                // 0이 없는 경우 단순 더함 (0이 존재했으면 상쇄되어서 안더하면 됨)
                if (ZERO == 0) {
                    sum += down.get(i);
                }
            }
        }

        // 1인 것들
        sum += ONE;

        System.out.println(sum);
    }
}
