package algorithm.programmers;

import java.util.*;

public class Babbling {

    static List<String> ava = Arrays.asList("aya", "ye", "woo", "ma");
    static Set<String> extra = new HashSet<>();
    boolean[] visited = new boolean[4];

    public int solution(String[] babbling) {
        int answer = 0;

        recursive("", 0);

        for (int x = 0; x < babbling.length; x++) {
            if (extra.contains(babbling[x])) {
                answer += 1;
            }
        }

        return answer;
    }

    public void recursive(String current,
                          int depth) {

        if (depth > 0) {
            extra.add(current);
        }

        if (depth == 4) {
            return;
        }

        for (int i = 0; i < ava.size(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                recursive(current + ava.get(i), depth + 1);
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
