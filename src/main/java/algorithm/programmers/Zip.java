package algorithm.programmers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Zip {

    public int[] solution(String msg) {
        int[] answer = {};

        List<Integer> answerList = new ArrayList<>();

        Map<String, Integer> map = new LinkedHashMap<>();

        // 초기화
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }

        String[] split = msg.split("");

        int idx = 0;

        while (idx + 1 < msg.length()) {
            String current = "";
            String preString = String.valueOf(msg.charAt(idx));

            for (int i = 1; idx + i < msg.length(); i++) {
                current = msg.substring(idx, idx + 1);
                if (!map.containsKey(current)) {
                    map.put(current, map.size() + 1);
                    break;
                }
                preString = current;
            }
            answerList.add(map.get(preString));
            idx = idx + preString.length();
        }


        for (int i = 0; i < msg.length() - 1; i++) {
            if (map.containsKey(split[i])) {
                String beforeKey = split[i];
                String newKey = split[i];
                for (int x = i + 1; x <= msg.length(); x++) {
                    newKey += split[x];
                    if (!map.containsKey(newKey)) {
                        map.put(newKey, map.size() + 1);
                        answerList.add(map.get(beforeKey));
                        break;
                    }
                    beforeKey = newKey;
                }
            }
        }

        System.out.println(map);
        System.out.println(answerList);
        return answer;
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        String inputs = "KAKAO";
        zip.solution(inputs);
    }
}
