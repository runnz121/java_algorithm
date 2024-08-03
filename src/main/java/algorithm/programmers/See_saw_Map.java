package algorithm.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/152996
public class See_saw_Map {

    public long solution(int[] weights) {
        long answer = 0;
        // 몸무게 기준으로 동일한 몸무게가 얼마나 있는지 확인
        Map<Double, Integer> weightMap = new HashMap<>();
        Arrays.sort(weights);

        for (int w : weights) {

            double a = w * 1.0;
            // 2 & 3 있는 경우
            double b = (w * 2.0) / 3.0;
            // 2 & 4 있는 경우
            double c = (w * 2.0) / 4.0;
            // 3 & 4 있는 경우
            double d = (w * 3.0) / 4.0;

            if (weightMap.containsKey(a)) {
                answer += weightMap.get(a);
            }
            if (weightMap.containsKey(b)) {
                answer += weightMap.get(b);
            }
            if (weightMap.containsKey(c)) {
                answer += weightMap.get(c);
            }
            if (weightMap.containsKey(d)) {
                answer += weightMap.get(d);
            }
            weightMap.put((w * 1.0), weightMap.getOrDefault((w * 1.0), 0) + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        See_saw_Map ss = new See_saw_Map();
        int[] inputs = {100,180,360,100,270};
        ss.solution(inputs);
    }
}
