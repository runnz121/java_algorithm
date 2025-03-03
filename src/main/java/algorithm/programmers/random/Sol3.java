package algorithm.programmers.random;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Sol3 {

    public int solution(int[] bid, int[] ask, int n) {
        int answer = 0;

        // bid 에서는 최대한 큰거
        // ask 에서는 최대한 작게

        Integer[] bidArr = Arrays.stream(bid).boxed().toArray(Integer[]::new);
        Arrays.sort(bidArr, Collections.reverseOrder());
        Arrays.sort(ask);

        int sumA = 0;
        int sumB = 0;
        for (int idx = 0; idx < (n / 2); idx++) {

            sumA += bidArr[idx];
            sumB += ask[idx];

            answer = Math.max(answer, sumA - sumB);
        }

        return answer;
    }

    public static void main(String[] args) {
        Sol3 s3 = new Sol3();

        // 1
        int[] bid = new int[]{100, 50, 150};
        int[] ask = new int[]{120, 200, 160};
        int n = 2;

        // 2
        int[] bid1 = new int[]{10, 10, 10};
        int[] ask1 = new int[]{5, 5, 5};
        int n1 = 6;

        // 3
        int[] bid3 = new int[]{5, 5, 5, 5};
        int[] ask3 = new int[]{10, 10, 10, 10};
        int n3 = 6;

        // 4
        int[] bid4 = new int[]{8, 10, 9};
        int[] ask4 = new int[]{7, 4, 9};
        int n4 = 6;

        int ans = s3.solution(bid, ask, n);
        int ans1 = s3.solution(bid1, ask1, n1);
        int ans2 = s3.solution(bid3, ask3, n3);
        int ans3 = s3.solution(bid4, ask4, n4);

        System.out.println(ans3);
    }
}
