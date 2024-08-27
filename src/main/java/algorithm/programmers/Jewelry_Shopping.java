package algorithm.programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/67258?language=java
public class Jewelry_Shopping {

    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int length = Integer.MAX_VALUE;
        int gemKinds = new HashSet<>(Arrays.asList(gems)).size();

        int left = 0;
        Map<String, Integer> jewerlyMap = new HashMap<>();

        for (int right = 0; right < gems.length; right++) {
            jewerlyMap.put(gems[right], jewerlyMap.getOrDefault(gems[right], 0) + 1);

            // 보석이 중복인 경우 보석 갯수를 줄이고 시작 인덱스를 증가
            while (jewerlyMap.get(gems[left]) > 1) {
                jewerlyMap.put(gems[left], jewerlyMap.get(gems[left]) - 1);
                left += 1;
            }

            // 맵에 보석의 종류가 모두 존재하면 모두 탐색완료
            if (jewerlyMap.size() == gemKinds && length > (right - left)) {
                length = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Jewelry_Shopping js = new Jewelry_Shopping();
        String[] inputs = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        js.solution(inputs);
    }
}
