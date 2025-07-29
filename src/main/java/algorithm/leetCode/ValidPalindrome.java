package algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        char[] charArray = s.toCharArray();

        List<Character> collections = new ArrayList<>();

        for (char ss : charArray) {
            boolean letterOrDigit = Character.isLetterOrDigit(ss);
            if (letterOrDigit) {
                char lowerCase = Character.toLowerCase(ss);
                collections.add(lowerCase);
            }
        }

        int left = 0;
        int right = collections.size() - 1;
        while (left < right) {


            if (collections.get(left) != collections.get(right)) {
                return false;
            }

            left += 1;
            right -= 1;
        }

        return true;
    }

    public static void main(String[] args) {

        ValidPalindrome vp = new ValidPalindrome();

        boolean palindrome = vp.isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(palindrome);
    }
}
