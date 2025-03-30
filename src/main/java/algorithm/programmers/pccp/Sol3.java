package algorithm.programmers.pccp;

import java.util.Arrays;

public class Sol3 {

    public String[] solution(int[][] queries) {

        String[] answer = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int gen = queries[i][0];
            int pos = queries[i][1];

            answer[i] = backTracking(gen, pos);
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    private String backTracking(int gen,
                                int pos) {

        if (gen == 1) return "Rr";

        // pos - 1 은 인덱스 0 기준으로 계산하기 위함
        // 부모 = n-1 세대의 ((pos - 1) / 4 + 1)번째
        int parentIndex = (pos - 1) / 4 + 1;
        int position = (pos - 1) % 4;  // 자식 내 위치 (0,1,2,3)
        String parentTrait = backTracking(gen - 1, parentIndex);

        // 순종인 경우
        if (parentTrait.equals("RR")) return "RR";
        if (parentTrait.equals("rr")) return "rr";

        // 잡종인 경우
        if (position == 0) {
            return "RR";
        } else if (position == 1 || position == 2) {
            return "Rr";
        } else {  // pos == 3
            return "rr";
        }
    }


    public static void main(String[] args) {
        Sol3 s3 = new Sol3();
        int[][] query = new int[][]{{3, 5}};
        s3.solution(query);
    }
}
