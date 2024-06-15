package algorithm.programmers;

import java.util.PriorityQueue;

//https://school.programmers.co.kr/learn/courses/30/lessons/42626
public class More_Hotter_Heap {

    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            que.offer(scoville[i]);
        }
        while (!que.isEmpty()) {
            if (que.peek() >= K) {
                break;
            }
            Integer out = que.poll();
            if (que.size() == 0 && out < K) {
                answer = -1;
                break;
            }
            Integer out2 = que.poll();
            Integer newOne = out + out2 * 2;
            answer += 1;
            que.offer(newOne);
        }
        if (scoville.length == 0) {
            return -1;
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        More_Hotter_Heap mhh = new More_Hotter_Heap();
        int[] inputs = {7, 2};
        mhh.solution(inputs, 7);
    }
}
