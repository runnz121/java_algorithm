package algorithmKotlin.leetCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        // 모두 소문자
        s = s.toLowerCase();
        // 영어 숫자 아닌거 제외
        String regex = "[0-9a-z]+";

        String regexedString = "";
        for (int k = 0; k < s.length(); k++) {
            if (String.valueOf(s.charAt(k)).matches(regex)) {
                regexedString += s.charAt(k);
            }
        }

        int start = 0;
        int end = regexedString.length() - 1;

        while(true) {
            if (start > end) {
                return true;
            }
            if (regexedString.charAt(start) != regexedString.charAt(end)) {
                return false;
            }
            start += 1;
            end -= 1;
        }
    }

    public boolean isPalindrome_answer(String s) {
        // 정규식으로 유효한 문자만 추출한 다음 모두 소문자로 변경
        String s_filtered = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        // 문자열을 뒤집은 다음 String으로 변경
        String s_reversed = new StringBuilder(s_filtered).reverse().toString();
        // 두 문자열이 동일한지 비교
        return s_filtered.equals(s_reversed);
    }

    public static void main(String[] args) {
        String sample ="A man, a plan, a canal: Panama";

        ValidPalindrome validPalindrome = new ValidPalindrome();
        boolean res = validPalindrome.isPalindrome(sample);
        System.out.println(res);
    }
}
