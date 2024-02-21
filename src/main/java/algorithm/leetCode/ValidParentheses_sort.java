package algorithm.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class ValidParentheses_sort {

    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> hashTable = new HashMap<>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        int length = s.length();

        for (int i =  0; i < length; i++) {
            // 닫힌게 아니면 스택에 푸시
            if (!hashTable.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            }
            // 중간에 스택이 빈 경우 or 열림 괄호가 아닌 경우
            else if (stack.isEmpty() || hashTable.get(s.charAt(i)) != stack.pop()) {
                System.out.println(hashTable.get(s.charAt(i)));
                return false;
            }
        }
        // 길이만큼 순회후 스택은 비어있어야 한다.
        return stack.size() == 0;
    }

    public static void main(String[] args) {

        ValidParentheses_sort vs = new ValidParentheses_sort();

        String s = "()";
        boolean res = vs.isValid(s);
//        String s1 = "()[]{}";
//        boolean res1 = vs.isvalid(s1);
//        String s2 = "(]";
//        boolean res2 = vs.isvalid(s2);

        System.out.println(res);
//        System.out.println(res1);
//        System.out.println(res2);
    }
}
