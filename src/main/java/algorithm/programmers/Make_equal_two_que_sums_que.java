package algorithm.programmers;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/118667
public class Make_equal_two_que_sums_que {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long que1Sum = Arrays.stream(queue1).sum();
        long que2Sum = Arrays.stream(queue2).sum();

        // 최대 작업 횟수 = 원형 큐 기준으로 생각 que1 : 2n - 1, que2 : n - 2 까지 이동 가능
        int totalLength = queue1.length * 3 - 3;

        Queue<Integer> que1 = Arrays.stream(queue1).boxed().collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> que2 = Arrays.stream(queue2).boxed().collect(Collectors.toCollection(LinkedList::new));

        while (answer <= totalLength) {

            if (que1Sum == que2Sum) {
                break;
            }

            if (que1Sum > que2Sum) {
                int que1Out = que1.peek() != null ? que1.peek() : 0;
                que2.add(que1.poll());
                que2Sum += que1Out;
                que1Sum -= que1Out;
            }

            else {
                int que2Out = que2.peek() != null ? que2.peek() : 0;
                que1.add(que2.poll());
                que1Sum += que2Out;
                que2Sum -= que2Out;
            }

            answer ++;
        }
        return answer > totalLength ? - 1: answer;
    }

    public static void main(String[] args) {
        Make_equal_two_que_sums_que mtqsq = new Make_equal_two_que_sums_que();
        int[] que1 = {3, 2, 7, 2};
        int[] que2 = {4, 6, 5, 1};

        int[] que11 = {1, 2, 1, 2};
        int[] que22 = {1, 10, 1, 2};

        int[] que111 = {1, 1};
        int[] que222 = {1, 5};
//        mtqsq.solution(que1, que2);
//        mtqsq.solution(que11, que22);
        mtqsq.solution(que111, que222);
    }
}
