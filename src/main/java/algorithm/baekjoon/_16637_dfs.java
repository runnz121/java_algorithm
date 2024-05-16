package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _16637_dfs {

    static int max = Integer.MIN_VALUE;
    static ArrayList<Integer> numbers = new ArrayList<>();
    static ArrayList<Character> operands = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer t = Integer.parseInt(st.nextToken());
        String cal = br.readLine();

        for (int i = 0; i < t; i++) {
            if (i % 2 == 0) {
                numbers.add(cal.charAt(i) - '0');
            } else {
                operands.add(cal.charAt(i));
            }
        }

        int startNumber = numbers.get(0);
        dfs(0, startNumber);
        System.out.println(max);
    }

    private static void dfs(int now,
                            int sum) {

        if (now >= operands.size()) {
            max = Math.max(max, sum);
            return;
        }

        // 괄호가 없는 경우 먼저 계산
        int one = calculate(now, sum, numbers.get(now + 1));
        dfs(now + 1, one);

        // 괄호가 있는 경우
        if (now + 1 < operands.size()) {
            // res 오른쪽에 있는 값을 계산
            int two = calculate(now + 1, numbers.get(now + 1), numbers.get(now + 2));
            int res = calculate(now, sum, two);
            // 현재 res와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자 인덱스를 넘김
            dfs (now + 2, res);
        }
    }

    public static int calculate(int op_idx,int a, int b) {
        switch(operands.get(op_idx)) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 1;
    }
}