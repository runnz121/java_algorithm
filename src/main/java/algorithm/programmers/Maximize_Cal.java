package algorithm.programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/67257
public class Maximize_Cal {

    static long MAX = 0;
    static List<Long> numList = new ArrayList<>();
    static List<Character> optList = new ArrayList<>();
    static List<Character> optKind = new ArrayList<>();
    static boolean[] visit;


    public long solution(String expression) {

        splitExp(expression);

        visit = new boolean[optKind.size()];
        List<Character> order = new ArrayList<>();
        priorityOrder(order);

        System.out.println(MAX);
        return MAX;
    }

    // 주어진 입력 문자열에서 숫자와 연산자를 분리
    private static void splitExp(String exp) {
        char[] arr = exp.toCharArray();
        Set<Character> optSet = new HashSet<>();
        StringBuilder numberStr = new StringBuilder();

        for (char c : arr) {
            if (c == '+' || c == '-' || c == '*') {
                optSet.add(c);
                optList.add(c);
                numList.add(Long.valueOf(numberStr.toString()));
                numberStr = new StringBuilder();
            } else {
                numberStr.append(c);
            }
        }
        numList.add(Long.valueOf(numberStr.toString()));
        optKind = new ArrayList<>(optSet);
    }

    // 연산자의 우선순위 구하기
    private static void priorityOrder(List<Character> order) {
        // 재귀로 연산자 우선순위 섞어서 계산함
        if (order.size() == optKind.size()) {
            // 정해진 우선순위에 맞게 계산
            Long now = calc(order);
            // 계산된 값과 최대값을 비교/갱신
            if (now > MAX) {
                MAX = now;
            }
            return;
        }

        for (int i = 0; i < optKind.size(); i++) {
            if (visit[i]) continue;
            visit[i] = true;
            order.add(optKind.get(i));
            priorityOrder(order);
            visit[i] = false;
            order.remove(order.size() - 1);
        }
    }

    // 연산자의 우선순위에 맞춰 계산
    private static Long calc(List<Character> order) {
        List<Long> copyNumList = new ArrayList<>(numList);
        List<Character> copyOptList = new ArrayList<>(optList);

        for (char opt : order) {
            for (int i = 0; i < copyOptList.size(); i++) {
                if (opt == copyOptList.get(i)) {
                    // 현재 선택된 연산자의 인덱스 기준으로 앞 뒤 숫자를 해당 연산자로 계산하고 다시 그 인덱스에 할당
                    copyNumList.set(i, calcOpt(copyNumList.get(i), copyNumList.get(i + 1), opt));
                    // 계산된 다음 숫자 삭제
                    copyNumList.remove(i + 1);
                    // 계산된 해당 연산자 삭제
                    copyOptList.remove(i);
                    // 다시 이전 인덱스부터 계산하도록 -- 처리
                    i--;
                }
            }
        }
        return Math.abs(copyNumList.get(0));
    }

    private static Long calcOpt(long a, long b, char opt) {
        long res = 0;
        if (opt == '+') {
            res = a + b;
        } else if (opt == '-') {
            res = a - b;
        } else {
            res = a * b;
        }
        return res;
    }

    public static void main(String[] args) {
        Maximize_Cal mc = new Maximize_Cal();
        long solution = mc.solution("50*6-3*2");
        System.out.println(solution);
    }
}
