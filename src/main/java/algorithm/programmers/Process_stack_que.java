package algorithm.programmers;

import java.util.*;

public class Process_stack_que {

    public int solution(int[] priorities, int location) {
        int answer = 0;

        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

        // 초기화
        for (int i = 0; i < priorities.length; i++) {
            que.offer(priorities[i]);
        }

        // que 제거
        while (!que.isEmpty()) {

            for (int i = 0; i < priorities.length; i++) {

                if (que.peek() == priorities[i]) {
                    que.poll();
                    answer ++;

                    if (location == i) {
                        return answer;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Process_stack_que psq = new Process_stack_que();
        int[] inputs = {2, 1, 3, 2};
        int location = 2;
        psq.solution(inputs, location);
    }
}
