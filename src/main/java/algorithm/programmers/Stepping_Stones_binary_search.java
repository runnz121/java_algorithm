package algorithm.programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/64062
public class Stepping_Stones_binary_search {

    public int solution(int[] stones, int k) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        int answer = 0;
        while (left <= right) {

            int mid = (left + right) / 2;
            if (check(mid, k, stones)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
        return answer;
    }

    private boolean check(int mid,
                          int k,
                          int[] stones) {
        int cnt = 0;
        for (int i = 0; i < stones.length; i++) {
            // mid 보다 작을 경우 건널 수가 없음으로 이때 cnt 해준다.
            if (stones[i] < mid) {
                cnt++;
                if (cnt >= k) {
                    return false;
                }
            // 건널 수 있으면 cnt 초기화
            } else {
                cnt = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Stepping_Stones_binary_search ss = new Stepping_Stones_binary_search();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        ss.solution(stones, 3);
//        int[] stones1 = {1,1,1,1,1,1};
//        ss.solution(stones1, 2);
    }
}
