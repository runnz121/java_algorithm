package algorithm.programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/67257
public class Maximize_Cal {

    static Map<Integer, String> priority = new HashMap<>();
    static List<String> operation = Arrays.asList("+", "-", "*");

    public long solution(String expression) {
        long answer = 0;

        priority = new HashMap<>();
        String[] split = expression.split("");
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < split.length; i++) {
            String s = split[i];

        }


        for (int i = 0; i < split.length; i++) {
            if (operation.contains(split[i]) == false) {
                stack.push(Integer.parseInt(split[i]));
            }
        }


        return answer;
    }

    public void updatePriority() {

    }

    public static void main(String[] args) {
        Maximize_Cal mc = new Maximize_Cal();
        long solution = mc.solution("50*6-3*2");
        System.out.println(solution);
    }
}
