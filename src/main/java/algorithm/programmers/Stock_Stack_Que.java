package algorithm.programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


//https://school.programmers.co.kr/learn/courses/30/lessons/42584?language=java
public class Stock_Stack_Que {

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            // 최초 감소한다면
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // 주식의 감소시점을 값으로 할당
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        // 감소하지 않았던 인덱스의 값을 빼내면서 값을 할당
        while (!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
        Stock_Stack_Que ssq = new Stock_Stack_Que();
        int[] inputs = {1, 2, 3, 2, 3};
        ssq.solution(inputs);
    }
}
