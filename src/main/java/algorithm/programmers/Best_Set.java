package algorithm.programmers;

import java.util.*;
// https://school.programmers.co.kr/learn/courses/30/lessons/12938
public class Best_Set {

    public int[] solution(int n, int s) {

        // 집합의 합보다 자연수개수가 많은 경우
        if (n > s) {
            return new int[]{-1};
        }

        int[] answer = new int[n];

        // 정답 배열을 s / n 으로 채운다 (몫으로 채움)
        Arrays.fill(answer, s / n);

        // 정답 배열 중 나머지 만큼 길이를 +1 시킨다
        for (int i = 0; i < s % n; i++) {
            answer[i] ++;
        }

        Arrays.sort(answer);

        return answer;
    }

    public static void main(String[] args) {
        Best_Set bs = new Best_Set();
        bs.solution(2, 9);
    }
}
