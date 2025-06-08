package company.oHousePay;

import java.util.*;

public class Sol1 {

    public int solution(String s) {
        int answer = 0;

        String[] split = s.split("");

        Map<String, Integer> wordFrequency = new LinkedHashMap<>();

        // 빈도수 첵크
        for (int x = 0; x < s.length(); x++) {
            String chars = split[x];
            wordFrequency.put(chars, wordFrequency.getOrDefault(chars, 0) + 1);
        }

        wordFrequency.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x -> wordFrequency.put(x.getKey(), x.getValue()));

        Set<String> keySet = wordFrequency.keySet();

        List<String> keyList = new ArrayList<>(keySet);

        for (int i = 0; i < keyList.size(); i++) {
            int cnt = i / 9;
            answer += cnt; // 해당 인덱스에 몇개 더 있는지
            answer += wordFrequency.get(keyList.get(i)); // 해당 키패드를 몇번더 눌러야 하는지
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Sol1 sol1 = new Sol1();
        String input = "ohouse";
        sol1.solution(input);
    }
}
