package algorithm.baekjoon;

import java.io.*;
import java.util.*;

//예제 입력 1 복사
//        6
//        10 20 10 30 20 50
//        예제 출력 1 복사
//        4
// -1,000,000,000 ≤ Ai ≤ 1,000,000,000)


public class _12738_binary_search {

    static long N;
    static List<Long> lists;
    static long maxLength = 0L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(br.readLine());

        for (int i = 0; i < N; i++ ) {
            lists.add(Long.parseLong(st.nextToken()));
        }

        long left = 0;
        long right = N - 1;

        while (left < right) {
            long mid = left + right / 2;
            long currentTarget = lists.get((int) mid);
            long length = 1L;
            for (long k = mid; k < right - mid - 1; k++) {
                Long data = lists.get((int) k);
                if (currentTarget < data) {
                    currentTarget = data;
                    length += 1;
                }
            }
            maxLength = Math.max(maxLength, length);

            if (length > maxLength) {

            } else {

            }
        }
    }
}
