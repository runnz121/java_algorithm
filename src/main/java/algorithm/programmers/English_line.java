package algorithm.programmers;

import java.util.*;

public class English_line {

    public int[] solution(int n, String[] words) {
        int[] answer = {};

        Map<Integer, List<String>> map = new HashMap<>();

        for (int k = 0; k < n; k++) {
            map.put(k + 1, new ArrayList<>());
        }

        for (int i = 0; i < words.length; i++) {
            int idx = (i % n) + 1;

            for (int k = 1; k <= n; k++) {
                if (map.get(k)!= null && map.get(k).contains(words[i]) ||
                words[i].split("")[words[i].length() - 1].equals(words[i - 1].split("")[0])) {

                    System.out.println(Arrays.toString(new int[]{idx, (i % n) + 1}));
                    return new int[]{idx, (i % n) + 1};
                }
            }

            List<String> theList = map.get(idx);
            theList.add(words[i]);
        }
        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        English_line el = new English_line();
        String[] inputs = List.of("hello", "one", "even", "never", "now", "world", "draw").toArray(new String[0]);
        el.solution(2, inputs);
    }
}
