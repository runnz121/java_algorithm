package algorithm.programmers;

import java.util.Arrays;

public class H_index_sort {

    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int hIndex = citations[i];
            if (hIndex >= citations.length - i) {
                // 이 때 이후로는 무조건 작아짐
                answer = citations.length - i;
                break;
            }
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        H_index_sort his = new H_index_sort();
        int[] input = {3, 0, 6, 1, 5};
        his.solution(input);
    }
}
