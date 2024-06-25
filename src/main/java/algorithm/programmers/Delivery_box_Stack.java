package algorithm.programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/131704
public class Delivery_box_Stack {

    public int solution(int[] order) {
        int answer = 0;
        int idx = 0;
        Deque<Integer> stackSub = new ArrayDeque<>();

        for (int i = 1; i <= order.length; i++) {
            stackSub.push(i);

            while (!stackSub.isEmpty()) {
                if (stackSub.peek() == order[idx]) {
                    stackSub.pop();
                    idx ++;
                    answer ++;
                }
                else break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Delivery_box_Stack db = new Delivery_box_Stack();
        int[] inputs = {4, 3, 1, 2, 5};
        int[] inputs1 = {5, 4, 3, 2, 1};
        db.solution(inputs);
    }
}


