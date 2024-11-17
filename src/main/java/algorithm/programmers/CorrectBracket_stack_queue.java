package algorithm.programmers;

import java.util.ArrayDeque;
import java.util.Deque;

// https://school.programmers.co.kr/learn/courses/30/lessons/12909
public class CorrectBracket_stack_queue {

	boolean solution(String s) {

		Deque<String> stack = new ArrayDeque<>();
		String[] inputs = s.split("");

		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i].equals("(")) {
				stack.add(inputs[i]);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}

		return stack.isEmpty();
	}

	public static void main(String[] args) {

		CorrectBracket_stack_queue cs = new CorrectBracket_stack_queue();
		boolean answer = cs.solution("()()");
	}
}
