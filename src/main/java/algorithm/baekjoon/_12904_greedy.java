package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문제 접근방법
 *
 * 1. 해당 문제는 S > T 로 만드는 방법을 물어뢌지만 실제로는 T > S 로 만들 수 있는지를 봐야한다.
 * - 정방향으로 가면 문제 가지수가 너무 많아 지기 때문
 * 2. 마지막 문자 기준으로
 * "A" 인 경우 -> 문제에서는 A 를 단순 추가하는 경우에 나올 수 있음으로 > 여기서는 단순 제거
 * "B" 인 경우 -> 문제에서는 문자열을 뒤집고 B를 추가함으로 > 여기서는 B 제거 후 문자열 뒤집기
 *
 */
public class _12904_greedy {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();

        boolean b = recursvie(s1, s2);
        System.out.println(b ? 1 : 0);
    }

    static boolean recursvie(String s1, String s2) {

        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() > s2.length()) {
            return false;
        }

        // 마지막 문자 기준으로 결정
        char last = s2.charAt(s2.length() - 1);

        // 마지막 문자가 'A' 인 경우 == 마지막만 제거
        if (last == 'A') {
            return recursvie(s1, s2.substring(0, s2.length() - 1));
        }

        // 마지막 문자가 'B' 인 경우 마지막 제거 후 뒤집기
        else {
            String reduced = s2.substring(0, s2.length() - 1);
            String reversed = new StringBuilder(reduced).reverse().toString();
            return recursvie(s1, reversed);
        }
    }
}

