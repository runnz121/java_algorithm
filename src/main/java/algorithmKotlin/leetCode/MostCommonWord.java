package algorithmKotlin.leetCode;

import java.util.*;

public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> countedMap = new HashMap<>();

        // 정규표현식 -> 단어가 아닌 것 : \W
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");

        for (String regexWord : words) {
            if (!bannedSet.contains(regexWord)) {
                // 존재하지 않으면 기본값을 0으로 지정 추출 후 +1 지정
                countedMap.put(regexWord, countedMap.getOrDefault(regexWord, 0) + 1);
            }
        }
        // 가장 흔하게 등장하는 단어확인
        return Collections.max(countedMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {

        String paragraph = "a, a, a, a, b,b,b,c, c";
        String[] banned = {"a"};

        MostCommonWord ms = new MostCommonWord();
        String res = ms.mostCommonWord(paragraph, banned);
        System.out.println(res);
    }
}
