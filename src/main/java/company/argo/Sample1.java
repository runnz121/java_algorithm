package company.argo;

import java.util.Stack;

public class Sample1 {

//    문자열을 입력받아 연속되는 문자를 압축해서 출력하시오.
//    예: "aaabbcccc" → "a3b2c4"
    public String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                sb.append(s.charAt(i - 1)).append(count);
                count = 1;
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    // 괄호 문자열이 주어졌을 때 유효한지 확인
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

}

