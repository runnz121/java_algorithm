package oHousePay;

import java.util.Arrays;
import java.util.Stack;

public class SAmple {
    public static void main(String[] args) {
        int[] balls1 = {4, 7, -5};
        int[] balls2 = {3, -3};
        int[] balls3 = {8, 4, -13};

        System.out.println("Output 1: " + arrayToString(simulateCollisions(balls1)));
        System.out.println("Output 2: " + arrayToString(simulateCollisions(balls2)));
        System.out.println("Output 3: " + arrayToString(simulateCollisions(balls3)));
    }

    private static int[] simulateCollisions(int[] balls) {
        Stack<Integer> stack = new Stack<>();

        for (int ball : balls) {
            processCollision(stack, ball);
        }

        // Convert Stack to int[]
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    private static void processCollision(Stack<Integer> stack, int newBall) {
        while (!stack.isEmpty() && ((stack.peek() > 0 && newBall < 0))) {
            int topBall = stack.pop();

            if (Math.abs(topBall) > Math.abs(newBall)) {
                newBall = topBall + newBall;
                break;
            } else if (Math.abs(topBall) < Math.abs(newBall)) {
                newBall = newBall + topBall;
                continue;
            } else {
                newBall = 0;
                break;
            }
        }

        if (newBall != 0) {
            stack.push(newBall);
        }
    }

    private static String arrayToString(int[] array) {
        return Arrays.toString(array);
    }
}
