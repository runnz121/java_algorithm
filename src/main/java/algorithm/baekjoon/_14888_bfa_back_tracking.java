package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class _14888_bfa_back_tracking {

    static int[] numbers;
    static int[] operands = new int[4]; // 0 : 덧셈, 1 : 뺄셈, 2 : 곱셈, 3 : 나눗셈
    static int N;
    static List<Long> answers = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        numbers = new int[N];

        // 숫자 입력
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st2.nextToken());
        }

        // 연산자 입력
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for (int k = 0; k < 4; k++) {
            operands[k] = Integer.parseInt(st3.nextToken());
        }

        dfs(1, numbers[0]);

        Collections.sort(answers);

        // 최대값
        System.out.println(answers.get(answers.size() - 1));
        System.out.println(answers.get(0));
    }

    private static void dfs(int afterNumIdx,
                            long cal) {
        if (afterNumIdx == N) {
            answers.add(cal);
        }
        // 마지막 숫자 인덱스가 이미 초과라면 종료
        if (afterNumIdx > N) {
            return;
        }
        for (int operandsIndex = 0; operandsIndex < operands.length; operandsIndex++) {
            // 덧셈
            if (operandsIndex == 0 && operands[operandsIndex] > 0) {
                operands[operandsIndex] -= 1;
                long res = cal + numbers[afterNumIdx];
                dfs(afterNumIdx + 1, res);
                operands[operandsIndex] += 1;
            }
            // 뺄셈
            if (operandsIndex == 1 && operands[operandsIndex] > 0) {
                operands[operandsIndex] -= 1;
                long res = cal - numbers[afterNumIdx];
                dfs(afterNumIdx + 1, res);
                operands[operandsIndex] += 1;
            }
            // 곱셈
            if (operandsIndex == 2 && operands[operandsIndex] > 0) {
                operands[operandsIndex] -= 1;
                long res = (long) cal * numbers[afterNumIdx];
                dfs(afterNumIdx + 1, res);
                operands[operandsIndex] += 1;
            }
            // 나눗셈
            if (operandsIndex == 3 && operands[operandsIndex] > 0) {
                operands[operandsIndex] -= 1;
                long res = cal / numbers[afterNumIdx];
                dfs(afterNumIdx + 1, res);
                operands[operandsIndex] += 1;
            }
        }
    }
}
