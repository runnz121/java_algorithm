package algorithm.baekjoon;

import java.io.*;
import java.util.*;

//예제 입력 1 복사
//        6
//        10 20 10 30 20 50
//        예제 출력 1 복사
//        4
// -1,000,000,000 ≤ Ai ≤ 1,000,000,000)


public class _12738_binary_search_lcs {

    static int N;
    static int[] lists;
    // 증가하는 수를 담는 리스트
    static int[] ascLists;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        lists = new int[N];

        for (int i = 0; i < N; i++ ) {
            lists[i] = Integer.parseInt(st.nextToken());
        }

        ascLists = new int[N + 1];
        ascLists[0] = -1_000_000_001;
        int len = 0;
        int idx = 0;
        // 수열의 처음부터 순회
        for (int i = 0; i < N; i++) {
            // 수열의 값 > 증가하는 수열의 현재 인덱스의 값 (비교되는 값은 증가하는 수열의 항상 마지막 인덱스이다)
            if (lists[i] > ascLists[len]) {
                // 증가하는 수열에 해당 값 추가
                ascLists[++len] = lists[i];
            } else {
                // 증가하는 수열에서 해당 하는 값이 들어갈 인덱스를 찾는다 (값이 덮어 씌어진다)
                idx = findIdxByBinarySearch(0, len, lists[i]);
                // 증가하는 수열에 존재하는 해당 인덱스에 값을 덮어 씌움
                ascLists[idx] = lists[i];
            }
        }
        System.out.println(len);
    }

    // 덮어씌어질 값이 존재하는 인덱스를 찾읍
    static int findIdxByBinarySearch(int left, int right, int target) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (ascLists[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
