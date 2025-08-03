package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 주의 할점
 * 1.left <= right (종료조건 = 들어가도록 하기)
 * 2.반환 대상이 인덱스임으로 값이랑 햇갈리지 않도록 주의
 * 3.타겟 값보다 비교갑싱 더 큰 경우 right 를 제어한다 - 1 -> 이거 까먹지 말기
 *
 */

// https://www.hackerrank.com/contests/lpdwarka2016/challenges/binary-search-basic/problem
public class Binary_Search {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();
        String[] splitS = br.readLine().split(" ");
        for (int i = 0; i < splitS.length; i++) {
            numbers.add(Integer.parseInt(splitS[i]));
        }
        Integer targetNum = Integer.parseInt(br.readLine());

        // idx
        int left = 0;
        int right = numbers.size() - 1;

        int answer = -1;

        while (left <= right) {

            // idx
            int mid = (left + right)/2;

            int target = numbers.get(mid);

            if (target == targetNum) {
                answer = mid;
                break;
            }

            if (target > targetNum) {
                right = mid - 1;
            }

            else {
                left = mid + 1;
            }
        }

        System.out.println(answer);

    }
}
