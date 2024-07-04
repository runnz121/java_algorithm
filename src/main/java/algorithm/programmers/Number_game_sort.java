package algorithm.programmers;


import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
public class Number_game_sort {

    public int solution(int[] A, int[] B) {
        int answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

        Integer[] A_Integer = Arrays.stream(A).boxed().toArray(Integer[]::new);
        Arrays.sort(A_Integer, Collections.reverseOrder());


        for (int i = 0; i < B.length; i++) {
            que.add(B[i]);
        }

        for (int i = 0; i < A.length; i++) {
            if (!que.isEmpty() && A_Integer[i] < que.peek()) {
                Integer poll = que.poll();
                if (poll > A_Integer[i]) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
