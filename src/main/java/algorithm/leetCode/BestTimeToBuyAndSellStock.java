package algorithm.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {

        int answer = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {

            if (stack.isEmpty()) {
                stack.push(prices[i]);
                continue;
            }

            if (stack.peek() > prices[i]) {
                stack.pop();
                stack.push(prices[i]);
            } else {
                Integer peek = stack.peek();
                answer = Math.max(prices[i] - peek, answer);
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        BestTimeToBuyAndSellStock bst = new BestTimeToBuyAndSellStock();
        int[] sample = new int[]{7,1,5,3,6,4};
        int i1 = bst.maxProfit(sample);
        System.out.println(i1);
    }
}
