package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 인덱스를 기준으로 탐색
 * 2.
 *
 */
public class _1725_stack_segmentTree_divideAndConquer {

    static int answer = 0;
    static int[] arr;
    static int N;
    static Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 2];

        // 인덱스 기준으로 배열을 설정 하고 값을 입력 받음
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st1.nextToken());
        }
        // 스택 초기화
        stack.push(arr[0]);

        for (int i = 1; i < N + 2; i++) {
            while (!stack.isEmpty()) {
                // 스택의 맨 윗 부분 확인
                int top = stack.peek();
                // 1. 새로 들어온 높이가 기존것보다 크거나 같다면 값을 저장 -> 스택에서 제거하지 않음
                if (arr[top] <= arr[i]) break;
                // 2. 새로 들어온 높이가 기존 것보다 작다면 -> 스택에 쌓여있는 것을 모두 제거한다. 이때 새로 들어오는 직사각형 높이보다 큰것만 조회
                // 같거나 낮으면 새로들어온 직사각형 넓이를 구할 수 있기 때문
                stack.pop();
                answer = Math.max(answer, arr[top] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        System.out.println(answer);
    }
}
