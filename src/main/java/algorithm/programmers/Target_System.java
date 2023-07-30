package algorithm.programmers;

import java.util.Arrays;

public class Target_System {

    public int solution(int[][] targets) {
        int answer = 0;
        int end = 0;
        // 2번째 원소 오름차순
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        for (int [] target : targets) {
            int s = target[0];
            int e = target[1];
            // 첫번째가 end 보다 작다면 넘어감 (범위 안에 존재한다는 뜻)
            if (s < end) continue;
            // 그렇지 않다면 범위를 벗어난다는 의미임으로 갱신 및 미사일 갯수 증가
            else end = e; answer += 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        Target_System ts = new Target_System();
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        ts.solution(targets);
    }
}
