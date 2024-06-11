package algorithm.programmers;

import java.util.*;

public class Zip {

    public int[] solution(String msg) {

        ArrayList<Integer> answerList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // 초기화
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        int idx = 0;
        while (idx + 1 < msg.length()) {
            String current = "";
            String preString = String.valueOf(msg.charAt(idx));

            for (int i = 1; idx + i <= msg.length(); i++) {
                current = msg.substring(idx, idx + i);
                if (!map.containsKey(current)) {
                    map.put(current, map.size() + 1);
                    break;
                }
                preString = current;
            }
            answerList.add(map.get(preString));
            idx = idx + preString.length();
        }

        if (idx == msg.length() - 1) {
            answerList.add(map.get(String.valueOf(msg.charAt(idx))));
        }

        System.out.println(map);
        return answerList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        String inputs = "KAKAO";
        zip.solution(inputs);
    }
}
