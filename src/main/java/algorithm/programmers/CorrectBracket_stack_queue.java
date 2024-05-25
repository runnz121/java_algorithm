package algorithm.programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class CorrectBracket_stack_queue {

	boolean solution(String s) {

		boolean answer = true;
		Deque<String> stack = new ArrayDeque<>();

		String[] inputs = s.split("");

		for (String bracket : inputs) {

			if ("(".equals(bracket)) {
				stack.add(bracket);
			} else if (")".equals(bracket)) {

				// 스택이 비어있을 경우를 확인
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}

		if (stack.isEmpty()) {
			answer = true;
		} else {
			answer = false;
		}

		return answer;
	}

	public static void main(String[] args) {

		CorrectBracket_stack_queue cs = new CorrectBracket_stack_queue();
		boolean answer = cs.solution("()()");
	}
}
