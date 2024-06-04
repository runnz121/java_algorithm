package algorithm.programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Double_priority_que_priorityQue {

    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minimumQue = new PriorityQueue<>();
        PriorityQueue<Integer> maximumQue = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String oper = split[0];
            Integer value = Integer.valueOf(split[1]);
            if (oper.equals("I")) {
                minimumQue.offer(value);
                maximumQue.offer(value);
            } else if (oper.equals("D") && split[1].equals("-1")) {
                if (!minimumQue.isEmpty()) {
                    Integer minimumPoll = minimumQue.poll();
                    maximumQue.remove(minimumPoll);
                }
            } else {
                if (!maximumQue.isEmpty()) {
                    Integer maximumPoll = maximumQue.poll();
                    minimumQue.remove(maximumPoll);
                }
            }
        }

        if (!maximumQue.isEmpty()) {
            answer[0] = maximumQue.poll();
        } else {
            answer[0] = 0;
        }

        if (!minimumQue.isEmpty()) {
            answer[1] = minimumQue.poll();
        } else {
            answer[1] = 0;
        }
        return answer;
    }

    public static void main(String[] args) {

        Double_priority_que_priorityQue dpq = new Double_priority_que_priorityQue();
        String[] inputs = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        dpq.solution(inputs);
    }
}
