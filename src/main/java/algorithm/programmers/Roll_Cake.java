package algorithm.programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/132265
public class Roll_Cake {

    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < topping.length; i++) {
            map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);
        }


        for (int i = 0; i < topping.length; i++) {
            // 현재 토핑을 중복 안되게 set에 저장
            set.add(topping[i]);
            // 해당 키로 존재하는 토핑 갯수를 뺀다
            map.put(topping[i], map.getOrDefault(topping[i], 0) - 1);
            // map 에서 토핑의 갯수가 0 이면 지워줌
            if(map.get(topping[i]) == 0) {
                map.remove(topping[i]);
            }
            // 종류가 달라도 사이즈 같은 경우는 토핑이 똑같이 있음으로
            if(set.size() == map.size()) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Roll_Cake rc = new Roll_Cake();
        int[] inputs = {1, 2, 1, 3, 1, 4, 1, 2};
        rc.solution(inputs);
    }
}
