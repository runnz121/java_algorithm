package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 접근법
 * - 종유석, 석순을 각각의 배열로 저장한다
 * - 이분 탐색을 위한 정렬 처리
 * - 종유석, 석순 각각 이진탐색으로 현재 높이 기준으로 몇개까지 충돌하는지 값을 반환 후 전체 충돌 개수를 계산
 * - 전체 충돌 개수 최소값을 계산 후 반환
 */
public class _3020_binary_search {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // 각각 종유석, 석순 담는 배열
        int [] low = new int[n / 2];
        int [] high = new int[n / 2];

        for (int i = 0; i < n / 2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            low[i] = a;
            high[i] = b;
        }

        // 이분 탐색을 위한 정렬 처리
        Arrays.sort(low);
        Arrays.sort(high);

        // 최대값으로 초기화 (모두 충돌할거라고 예상)
        int min = n;
        int cnt = 0;

        for (int i = 1; i < h + 1; i++) {

            // 각 종유석, 석순의 충돌 높이 총합
            int total = binary(0, n / 2, i, low) // 아래에서 위로 솟음
                      + binary(0, n / 2, h - i + 1, high); // 위에서 아래로 내려옴으로 (전체 높이  - 현재 높이 + 1)

            // 최소 충돌 수 갱신
            if (min == total) {
                cnt += 1;
                continue;
            }

            // 더 작은 값 나올시 갱신 및 초기화
            if (min > total) {
                min = total;
                cnt = 1;
            }
        }

        System.out.println(min + " " + cnt);
    }

    // 현재 높이에서 부딪히는 장애물 개수
    static int binary(int left, int right, int h, int [] arr) {

        while (left < right) {

            int mid = (left + right) / 2;

            if (arr[mid] >= h) {
                right = mid;
            }

            else {
                left = mid + 1;
            }
        }

        // 현재 높이 (h) 이상 값 (인덱스 찾고) 정렬되어있음으로 그 뒤는 모두다 충돌 함으로 전체 길이에서 높이 빼준다.
        return arr.length - right;
    }
}
