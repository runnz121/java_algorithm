package algorithm.programmers.pccp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sol4 {

    public long[] solution(int[][] program) {
        long[] answer = {};

        Arrays.sort(program, Comparator.comparingInt(a -> a[1]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
           if (a[0] != b[0]) {
               return Integer.compare(a[0], b[0]);
            } else {
               return Integer.compare(a[1], b[1]);
           }
        });

        int time = 0;
        int i = 0;
        int n = program.length;


        return answer;
    }

    public static void main(String[] args) {
        Sol4 s4 = new Sol4();
        int[][] sample = {{2, 0, 10}, {1, 5, 5}, {3, 5, 3}, {3, 12, 2}};
        s4.solution(sample);
    }
}
