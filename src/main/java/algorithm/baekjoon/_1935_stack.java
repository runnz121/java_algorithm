package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 연관문제 1918
 *
 */
public class _1935_stack {

    static int N;
    static double answer = 0.;
    static Deque<Double> stack = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 갯수 입력 받기
        N = Integer.parseInt(st.nextToken());
        // 후위 표현식 입력 받기
        String line = br.readLine();
        // 알파벳 대응 숫자 입력받기
        double[] arr = new double[N];
        for(int i = 0; i < arr.length; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            arr[i] = Double.parseDouble(st1.nextToken());
        }
        // 후위 순회식 순환
        for (int i = 0; i < line.length(); i++) {
            // 알파벳인 경우 -> 대응하는 숫자로 스택 넣기
            if ('A' <= line.charAt(i) && line.charAt(i) <= 'Z') {
                stack.push(arr[line.charAt(i) - 'A']);
            } else {
                if (!stack.isEmpty()) {
                    // 스택이 비어있지 않다면 연산 처리
                    operation(stack.pop(), stack.pop(), line.charAt(i));
                }
            }
        }
        System.out.printf("%.2f", answer);
    }

    // 괄호부분을 고려해서 결과값을 스택에 다시 넣는다.
    private static void operation(double first, double second, char operation) {
        switch (operation) {
            case '+':
                answer = second + first;
                stack.push(answer);
                break;
            case '-':
                answer = second - first;
                stack.push(answer);
                break;
            case '*':
                answer = second * first;
                stack.push(answer);
                break;
            case '/':
                answer = second / first;
                stack.push(answer);
                break;
            default:
                break;
        }
    }
}
