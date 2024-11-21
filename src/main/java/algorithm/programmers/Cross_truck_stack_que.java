package algorithm.programmers;

import java.util.Deque;
import java.util.LinkedList;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583?language=java
public class Cross_truck_stack_que {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Deque<Integer> que = new LinkedList<>();

        for(int i = 0; i < bridge_length; i++){
            que.offer(0);
        }

        int startIdx = 0;
        int currentBridgeWeight = 0;

        while (startIdx < truck_weights.length) {

            currentBridgeWeight -= que.poll();
            answer ++;

            if (currentBridgeWeight + truck_weights[startIdx] <= weight) {
                que.offer(truck_weights[startIdx]);
                currentBridgeWeight += truck_weights[startIdx++];
            }
            else que.offer(0);
        }
        // 마지막 트럭까지 다리를 건너는 경우를 확인
        return answer + bridge_length;
    }

    public static void main(String[] args) {

        Cross_truck_stack_que ctsq = new Cross_truck_stack_que();
        int[] inputs = {50, 30, 10, 10, 30, 10, 40};
        ctsq.solution(10, 100, inputs);
    }
}
