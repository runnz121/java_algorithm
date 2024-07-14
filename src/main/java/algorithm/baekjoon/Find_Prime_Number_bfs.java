package algorithm.baekjoon;

import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/42839?language=java
public class Find_Prime_Number_bfs {

    static Set<Integer> LISTS;
    static boolean[] checked = new boolean[7];

    public int solution(String numbers) {
        LISTS = new HashSet<>();
        int answer = 0;

        backTracking(0, "", numbers);

        for (Integer num : LISTS) {
            if (isPrime(num)) {
                answer++;
            }
        }
        System.out.println(LISTS);
        System.out.println(answer);
        return answer;
    }

    private static void backTracking(int depth,
                                     String ans,
                                     String numbers) {
        if (depth > numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (checked[i] == true) {
                continue;
            }
            checked[i] = true;
            LISTS.add(Integer.parseInt(ans + numbers.charAt(i)));
            backTracking(depth + 1, ans + numbers.charAt(i), numbers);
            checked[i] = false;
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n  % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Find_Prime_Number_bfs pf = new Find_Prime_Number_bfs();
        pf.solution("011");
    }
}
