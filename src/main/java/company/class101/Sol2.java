package company.class101;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * 괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻이다.
 * 올바른 괄호 규칙은 다음과 같다.
 * 1. 빈 문자열은 올바른 괄호 문자열이다.
 * 2. A가 올바른 괄호 문자열이라면, (A) 도 올바른 괄호 문자열이다.
 * 3. A와 B가 올바른 괄호 문자열이라면 AB도 올바른 괄호 문자열이다.
 *
 * 예를 들면 "()()", "(())()"는 올바른 괄호이다. 그러나 ")(", "())()" 등은 올바르지 않다.
 *
 * 괄호로만 이루어진 문자열이 담긴 두 배열이 있다.
 * 각 배열에 담긴 문자열은 올바르거나 혹은 올바르지 않다.
 * 예시는 다음과 같다.
 *
 * - arr1 = ["()", "(()", ")()", "()"]
 * - arr2 = [")()", "()", "(()"]
 *
 * 각 배열에서 문자열을 하나씩 선택해 이어 붙여 올바른 괄호를 만드는 방법이 모두 몇가지인지 세려고 한다.
 * 단 문자열을 이어붙일 떄 반드시 arr1 의 문자열이 앞에 오고, arr2의 문자열이 뒤에 와야 한다.
 * 또 두개를 이어 붙여서 만든 괄호 문자열이 같더라도 arr1이나 arr2에서 이전과는 다른 위치에 있는 문자열을 선택했다면 서로 다른 방법으로 세어야 한다.
 *
 * 1. arr1에서 첫번째 문자열 "()"를 선택하고 arr2에서 두번째 문자열 "()" 를 선택해서 이어 붙이면 "()()" 올바른 괄호가된다
 * 2. arr1에서 두번째 문자열"(()" 를 선택하고 arr2에서 첫번째 문자열 ")()"를 선택해 이어 붙이면 "(())()"로 올바른 괄호가된다
 * 3. arr1에서 네번째 문자열 "()"를 선택하고 arr2에서 두번째문자열"()"를 선택해서 이어 붙이면 "()()"로 올바른 괄호가된다.
 *
 * 괄호로만 이루어진 문자열이 담긴 배열 arr1, arr2가 매개변수로 주어질때 각 배열에서 문자열을 하나씩 선택해서 이어붙일때 올바른 괄호가되는 방법이 몇가지인지 return 하는 함수를 만들어라
 *
 * 입출력 예시
 * input :
 * arr1 : ["()", "(()", ")()", "()"]
 * arr2 : [")()", "()", "(()"]
 * return : 3
 *
 * input:
 * arr1 : ["()", "(()", "(", "(())"]
 * arr2 : [")()", "()", "(())", ")()"]
 * return : 8
 *
 */

public class Sol2 {

    // 열린 갯수 : idx
    static Map<Integer, Integer> arr2OpenBracketCount = new HashMap<>();
    static int[] check;

    static int answer;

    public long solution(String[] arr1,
                         String[] arr2) {

        for (int idx = 0; idx < arr2.length; idx++) {
            int openCount = countBracket(1, arr2[idx]);
            arr2OpenBracketCount.put(idx, openCount);
        }

        System.out.println(arr2OpenBracketCount);

        for (int idx = 0; idx < arr1.length; idx++) {
            int closeCount = countBracket(-1, arr1[idx]);
            if (arr2OpenBracketCount.containsKey(closeCount)) {
                Integer arr2Idx = arr2OpenBracketCount.get(closeCount);
                if (isValidBracket(arr1[idx], arr2[arr2Idx])) {
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
        return answer;
    }

    int countBracket(int flag, String ele) {
        int open = 0;
        int close = 0;

        char[] charArray = ele.toCharArray();
        for (char c : charArray) {
            if (c == ')') {
                open += 1;
            } else {
                close += 1;
            }
        }
        return flag > 0 ? open : close;
    }

    boolean isValidBracket(String arr1, String arr2) {
        String all = arr1 + arr2;
        char[] charArray = all.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : charArray) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Sol2 s2 = new Sol2();
        String[] arr1 = {"()", "(()", ")()", "()"};
        String[] arr2 = {")()", "()", "(()"};
        s2.solution(arr1, arr2);

        String[] arr11 = {"()", "(()", "(", "(())"};
        String[] arr22 = {")()", "()", "(())", ")()"};
        s2.solution(arr11, arr22);
    }
}
