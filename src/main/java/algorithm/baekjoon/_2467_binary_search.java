package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class _2467_binary_search {

    static long STD = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {

        List<Integer> lists = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());

        int[] answers = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < n; i++) {
            lists.add(Integer.parseInt(st.nextToken()));
        }

        // 두 수를 비교해야 하기 때문에 for문으로 전체 탐색 필요
        for (int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left <= right) {

                int mid = (left + right) / 2;
                int sums = lists.get(i) + lists.get(mid);

                // 음수도 존재할 수 있음으로 최대값 갱신은 절대값으로 지정
                if (STD > Math.abs(sums)) {
                    answers[0] = lists.get(i);
                    answers[1] = lists.get(mid);
                    STD = Math.abs(sums);
                }
                if (sums > 0) {
                    right  = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        Arrays.sort(answers);
        System.out.println(answers[0] + " " + answers[1]);
    }
}
