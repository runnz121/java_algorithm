package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 잘못된 접근 방법
 * - 타겟넘버를 발견했을때, 해당 인덱스 -- , 인덱스  ++ 로 찾으려고 했다 -> 이렇게 되면 시간 복잡도가 최악으로 O(N^2) 임으로 문제조건에 맞지 않음
 *
 * 올바른 접근 방법
 * - 처음 존재 인덱스, 마지막 존재 인덱스를 찾기 위해 각각을 이진탐색으로 진행
 * - 처음 인덱스를 찾는 방법 : 이진 탐색에서 오른족 인덱스 값을 줄여나가면서, 발견되는 원소를 마지막으로 선택
 *
 *
 * https://www.hackerrank.com/contests/lpdwarka2016/challenges/binary-search-advanced/problem
 */
public class Binary_Search_Advanced {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();

        // 숫자 문자열 인풋값 뒤에 공백으로 들어오는 케이스가 있어서 이를 제거
        String[] splits = br.readLine().trim().split("\\s+");
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(splits[i]));
        }

        String K = br.readLine();

        // 타겟 인덱스 입력값이 없는 케이스가 있어 이를 예외처리
        if (K == null || K.trim().isEmpty()) {
            System.out.println("-1 -1 0");
            return;
        }

        Integer target = Integer.parseInt(K);

        int firstIdx = findFirstIndex(numbers, target);
        int lastIdx = findLastIndex(numbers, target);

        if (firstIdx == -1 || lastIdx == -1) {
            System.out.println("-1 -1 0");
        }
        else {
            System.out.printf("%d %d %d", firstIdx, lastIdx, lastIdx - firstIdx + 1);
        }
    }

    public static int findFirstIndex(List<Integer> arr, int target) {

        int left = 0;
        int right = arr.size() - 1;

        int result = -1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (arr.get(mid) == target) {
                result = mid;
                right = mid - 1; // 이 부분이 핵심 -> 마지막 인덱스를 찾기 위해 계속 왼쪽으로 가서 값에 수렴하도록!!
            }
            else if (arr.get(mid) > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return result;
    }

    public static int findLastIndex(List<Integer> arr, int target) {

        int left = 0;
        int right = arr.size() - 1;

        int result = -1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (arr.get(mid) == target) {
                result = mid;
                left = mid + 1; // 이 부분이 핵심 -> 마지막 인덱스를 찾기 위해 계속 오른쪽으로 가서 값에 수렴하도록!!
            }
            else if (arr.get(mid) > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return result;
    }
}
