package algorithm.programmers;

import java.util.*;
import java.util.stream.Collectors;

public class Babbling {

    static List<String> ava = Arrays.asList("aya", "ye", "woo", "ma");
    static Set<String> extra = new HashSet<>();
    static boolean[] visited;

    public int solution(String[] babbling) {
        int answer = 0;

        for (int i = 2 ; i <= 4; i++) {
            visited = new boolean[ava.size()];
            for (int k = 0; k < 4; k++) {
                visited[k] = true;
                recursive(i, k, ava.get(k));
            }
        }

        List<String> extraLists = new ArrayList<>(extra.stream().toList());
        extraLists.addAll(ava);
        Set<String> collect = extraLists.stream().collect(Collectors.toSet());

        for (int x = 0; x < babbling.length; x++) {
            if (collect.contains(babbling[x])) answer += 1;
        }

        System.out.println(answer);
        System.out.println(collect);
        return answer;
    }

    public void recursive(int targetDepth,
                          int start,
                          String word) {

        if (targetDepth <= start) {
            extra.add(word);
            return;
        }

        for (int i = 0; i < ava.size(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                recursive(targetDepth, i + 1, word + ava.get(i));
                visited[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        Babbling bb = new Babbling();
        String[] inputs = {"aya", "yee", "u", "maa", "wyeoo"};
        String[] inputs1 = {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"};
        bb.solution(inputs1);
    }
}
