package algorithm.programmers;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/131701
public class Continuous_sub_array {

    public int solution(int[] elements) {

        Set<Integer> hashAnswer = new HashSet<>();

        List<Integer> toList = Arrays.stream(elements).boxed().collect(Collectors.toList());

        List<Integer> newList = new ArrayList<>();
        newList.addAll(toList);
        newList.addAll(toList);

        int differ = 1;
        while (true) {

            if (differ > elements.length) {
                break;
            }

            for (int i = 0; i < elements.length; i++) {
                for (int j = i + differ; j < i + differ + 1; j++) {

                    List<Integer> subList = newList.subList(i, j);
                    hashAnswer.add(subList.stream().mapToInt(Integer::intValue).sum());
                }
            }
            differ ++;
        }

        return hashAnswer.size();
    }

    public static void main(String[] args) {
        Continuous_sub_array ne = new Continuous_sub_array();
        int [] inputs = {7,9,1,1,4};
        ne.solution(inputs);
    }
}
