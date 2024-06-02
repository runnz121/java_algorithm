package algorithm.programmers;


import java.util.ArrayDeque;
import java.util.Deque;

public class Turnning_bracket_stack_que {

    public int solution(String s) {
        int answer = 0;
        char[] charArray = s.toCharArray();

        Deque<Character> que = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            que.add(charArray[i]);
        }

        for (int x = 0; x < s.length(); x++) {
            // 1. 이동
            Character popleft = que.pollFirst();
            que.addLast(popleft);
            // 2. 복사 deque 만듬
            Deque<Character> copyQue = new ArrayDeque<>(que);
            // 3. 스택
            Deque<Character> stack = new ArrayDeque<>();

            if (checkStack(copyQue, stack)) {
                answer ++;
            }
        }
        return answer;
    }

    private boolean checkStack(Deque<Character> copyQue,
                               Deque<Character> stack) {
        while(!copyQue.isEmpty()) {
            Character character = copyQue.pollFirst();
            if (character == '(' || character == '{' || character == '[') {
                stack.add(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (character == ')' && stack.peek() == '(') {
                    stack.pop();
                }
                else if (character == '}' && stack.peek() == '{') {
                    stack.pop();
                }
                else if (character == ']' && stack.peek() == '[') {
                    stack.pop();
                }
                else return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Turnning_bracket_stack_que tb = new Turnning_bracket_stack_que();
        String input = "[)(]";
        tb.solution(input);
    }
}

