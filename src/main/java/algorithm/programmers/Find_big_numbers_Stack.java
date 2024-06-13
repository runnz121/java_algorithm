package algorithm.programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class Find_big_numbers_Stack {

    public int[] solution(int[] numbers) {
        // 인덱스 기준으로 스택 생성
        Deque<Integer> stack = new ArrayDeque<>();
        int[] answer = new int[numbers.length];
        stack.push(0);

        for (int i = 1; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        return answer;
    }

    public static void main(String[] args) {
        Find_big_numbers_Stack fbn = new Find_big_numbers_Stack();
        int[] inputs = {2, 3, 3, 5};
        fbn.solution(inputs);
    }
}
