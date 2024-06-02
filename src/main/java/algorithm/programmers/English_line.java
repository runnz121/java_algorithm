package algorithm.programmers;

import java.util.*;

public class English_line {

    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        int turn = 2; // 턴을 나타내는 변수 (초기값은 2부터 시작)
        int round = 1; // 라운드를 나타내는 변수 (초기값은 1부터 시작)
        String previous = words[0]; // 이전 단어를 저장할 변수

        // 이미 나온 단어를 체크하는 리스트
        List<String> check = new ArrayList<>();
        check.add(previous); // 첫 단어 추가

        // 끝말잇기 시작
        for (int i = 1; i < words.length; i++) {
            String current = words[i]; // 현재 단어

            // 단어의 길이가 1 이하이면 끝냄
            if (current.length() <= 1) {
                break;
            }

            // 이미 나온 단어라면 끝냄
            if (check.contains(current)) {
                break;
            }

            // 이전 단어의 끝과 현재 단어의 시작이 다르면 끝냄
            if (previous.charAt(previous.length() - 1) != current.charAt(0)) {
                break;
            }

            turn++;
            previous = current;
            check.add(previous);

            if (turn > n) {
                turn = 1;
                round++;
            }

            if (i == words.length - 1) {
                turn = 0;
                round = 0;
            }
        }

        answer[0] = turn;
        answer[1] = round;

        return answer;
    }

    public static void main(String[] args) {
        English_line el = new English_line();
        String[] inputs = List.of("hello", "one", "even", "never", "now", "world", "draw").toArray(new String[0]);
        el.solution(2, inputs);
    }
}
