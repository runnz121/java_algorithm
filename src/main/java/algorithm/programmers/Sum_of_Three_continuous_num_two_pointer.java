package algorithm.programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/178870
public class Sum_of_Three_continuous_num_two_pointer {

    static int[] answer;
    static int size;

    public int[] solution(int[] sequence, int k) {
        answer = new int[]{-1, -1};
        size = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        long sums = 0L;

        while (true) {
            if (sums >= k) {
                if (sums == k && right - left < size) {
                    size = right - left;
                    answer[0] = left;
                    answer[1] = right - 1;
                }
                sums -= sequence[left++];
            } else {
                if (right == sequence.length) break;
                sums += sequence[right++];
            }
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public static void main(String[] args) {
        Sum_of_Three_continuous_num_two_pointer st = new Sum_of_Three_continuous_num_two_pointer();
        int[] inputs = {1, 2, 3, 4, 5};
        int k = 7;
        st.solution(inputs, k);
    }
}
