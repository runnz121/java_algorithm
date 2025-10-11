package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 문제 접근법
 * - 과일을 넣는 방향을 중요하지 않음
 * - 과일의 종류가 2개로 유지하고 과일을 넣을때마다 빈도수를 체크하고 갱신하는것이 핵심
 */
public class _30804_bruteforce {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");

        Deque<Integer> window = new ArrayDeque<>();
        // 과일의 빈도수를 저장
        int[] frequent = new int[10];

        int distinct = 0;
        int best = 0;

        for (String ele : arr) {
            int intX = Integer.parseInt(ele);

            // 오른쪽에 과일 추가
            window.addLast(intX);
            if (++frequent[intX] == 1) {
                distinct ++;
            }

            // 과일 3종류가 되면 왼쪽에서 빼서 2이하로 줄임
            while (distinct > 2) {
                Integer pollLeft = window.pollFirst();
                if (--frequent[pollLeft] == 0) {
                    distinct --;
                }
            }

            // 최대값 갱신
            best = Math.max(best, window.size());
        }

        System.out.println(best);
    }
}
