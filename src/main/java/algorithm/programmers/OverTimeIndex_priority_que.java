package algorithm.programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class OverTimeIndex_priority_que {

    public long solution(int n, int[] works) {
        long answer = 0;

        // 우선순위 큐 역순 정렬
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            que.offer(work);
        }

        for (int i = 0; i < n; i++) {
            int out = que.poll();
            if (out <= 0) {
                 break;
            }
            // 1시간씩 일한다고 하였음으로 큰 야근량 부터 1시간씩 뺴서 다시 큐에 넣음
            que.offer(out - 1);
        }

        while (!que.isEmpty()) {
            answer += Math.pow(que.poll(), 2);
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        int[] works = {4, 3, 3};
        int n = 4;
        OverTimeIndex_priority_que oti = new OverTimeIndex_priority_que();
        oti.solution(n, works);
    }
}
