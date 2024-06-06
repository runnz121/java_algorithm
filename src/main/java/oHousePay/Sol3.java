package oHousePay;

import java.util.Arrays;
import java.util.Stack;

public class Sol3 {

    public int[] solution(int[] balls) {

        Stack<Integer> stack = new Stack<>();

        for (int ball : balls) {

            while (!stack.isEmpty() && stack.peek() > 0 && ball < 0) {
                int topBall = stack.pop();

                if (Math.abs(topBall) > Math.abs(ball)) {
                    ball = topBall;
                } else if (Math.abs(topBall) < Math.abs(ball)) {
                    ball += topBall;
                } else {
                    ball = 0;
                    break;
                }
            }

            if (ball != 0) {
                stack.push(ball);
            }
        }

        System.out.println(stack);
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        Sol3 sol3 = new Sol3();
        int[] input = {4,7,-15};
        sol3.solution(input);
    }
}


