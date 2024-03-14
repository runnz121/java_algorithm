package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// 연관 문제 1935

// 스택에는 연산자만 넣고, 피연산자는 바로 출력
// 연산자 우선순위 지정
// - 현재 연산자 우선순위보다 큰 연산자가 스택 맨 위에 존재 -> 없을 때 까지 pop
// - ) 일 경우에는 ( 가 나올 떄까지 pop
// - 피연산자는 stringBuilder에 append
public class _1918_stack {
    static int N;
    static Deque<Character> stack = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();

        N = line.length();

        for (int i = 0; i < N; i++) {

            char now = line.charAt(i);
            switch (now) {
                // 연산자일 경우 우선순위를 비교한다.
                case '+':
                case '-':
                case '*':
                case '/':
                    // 현재 보다 스택 상위 연산자 우선순위가 더 높은 경우 -> 스택이 빌 떄까지 pop
                    while (!stack.isEmpty() &&
                            operationPriority(stack.peek()) >= operationPriority(now)) {

                        sb.append(stack.pop());
                    }
                    stack.push(now);
                    break;
                // 괄호 시작 -> 스택 넣음
                case '(':
                    stack.push(now);
                    break;
                // 괄호 종료 -> '(' 나올 떄까지 pop
                case ')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    // ')' 를 팝 해준다.
                    stack.pop();
                    break;
                // 피연산자는 바로 sb join
                default:
                    sb.append(now);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }

    private static int operationPriority(char oper) {
        // 괄호는 없음
        if (oper == '(' || oper == ')') {
            return 0;
        }
        if (oper == '+' || oper == '-') {
            return 1;
        }
        if (oper == '*' || oper == '/') {
            return 2;
        }
        return -1;
    }
}
