package algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses_divide_and_conquer_recursive {

    public List<Integer> diffWaysToCompute(String expression) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                for (int l : left) {
                    for (int r : right) {
                        if (c == '+') {
                            result.add(l + r);
                        } else if (c == '-') {
                            result.add(l - r);
                        } else {
                            result.add(l * r);
                        }
                    }
                }
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }
        return result;
    }


    public static void main(String[] args) {
        DifferentWaysToAddParentheses_divide_and_conquer_recursive dwt = new DifferentWaysToAddParentheses_divide_and_conquer_recursive();
        String expression = "2*6-4*3";
        dwt.diffWaysToCompute(expression);
    }
}
