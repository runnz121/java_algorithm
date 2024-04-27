package toss;

import java.util.*;

public class Sol2 {

    public int solution(String s) {

        List<Integer> answers = new ArrayList<>();

        int x = 0;
        while (true) {

            int start = x;
            int end = start + 3;

            if (start + 3 > s.length()) {
                break;
            }

            String substring = s.substring(x, end);

            if (substring.charAt(0) == substring.charAt(1)) {
                if (substring.charAt(1) == substring.charAt(2)) {
                    int answer = Integer.parseInt(substring);
                    answers.add(answer);
                }
            }

            x += 1;
        }

        if (answers.size() == 0) {
            return -1;
        }
        Collections.sort(answers);
        return answers.get(answers.size() - 1);
    }

    public static void main(String[] args) {
        Sol2 t = new Sol2();
        t.solution("1222333");
    }
}
