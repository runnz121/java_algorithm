package company.navercloud;
import java.util.*;

//        AND는 기본적인 비트 연산입니다.
//        예를 들어, K = 12 (2진수 01100), L = 21 (2진수 10101)이라면 아래와 같은 AND 연산 결과를 얻습니다:
//
//        01100
//        AND
//        10101
//        00100
//
//        AND 연산은 N개의 정수에 대해서도 확장할 수 있습니다. 예를 들어:
//
//        01100
//        AND 10101
//        AND 00100
//        = 00100
//
//        01100 (첫 번째 숫자)와 10101 (두 번째 숫자)의 AND는 00100이고,
//        이 결과를 다시 00100 (세 번째 숫자)와 AND 연산한 결과 역시 00100입니다.
//
//        함수 설명
//
//        정수로 이루어진 배열 A가 주어질 때,
//        A에서 일부 원소들로 이루어진 부분집합 중 모든 원소의 AND 결과가 0보다 큰
//        가장 큰 크기의 부분집합의 원소 개수를 반환하시오.
//
//        예시
//
//        A = [13, 7, 2, 8, 3] → 반환값: 3
//        가능한 부분집합은 [13, 7, 3]이고,
//        13 AND 7 AND 3 = 1 (양수)
//
//        A = [1, 2, 4, 8] → 반환값: 1
//        어떤 쌍도 AND 연산 결과가 0보다 크지 않음
//
//        A = [16, 16] → 반환값: 2
//        16 AND 16 = 16 (양수)
//
//        조건
//
//        N은 [1..100,000] 범위의 정수
//
//        A의 각 원소는 [1..1,000,000,000] 범위의 정수

public class Sol3 {

    public int solution(int[] A) {
        // 0번 비트 ~ 30번 비트까지, 각 비트가 1인 수들을 담을 리스트 배열
        List<Integer>[] groups = new ArrayList[31];
        for (int bit = 0; bit < 31; bit++) {
            groups[bit] = new ArrayList<>();
        }

        // 1) A를 한 번 훑으면서,
        //    각 숫자가 어떤 비트에 1이 켜져 있는지 확인해서
        //    해당 비트의 리스트에 추가
        for (int x : A) {
            for (int bit = 0; bit < 31; bit++) {
                // 숫자 x 의 bit 번째 비트가 1인지 확인 == bit 번째가 1인가 확인
                if ((x & (1 << bit)) != 0) {
                    groups[bit].add(x);
                }
            }
        }

        // 2) 가장 큰 그룹(=가장 많은 수가 1을 가진 비트 위치)의 크기를 찾는다
        int answer = 0;
        for (int bit = 0; bit < 31; bit++) {
            answer = Math.max(answer, groups[bit].size());
        }

        return answer;
    }

    public static void main(String[] args) {
        Sol3 s3 = new Sol3();
        int [] x = new int[]{13, 24};
        int solution = s3.solution(x);
        System.out.println(solution);
    }
}
