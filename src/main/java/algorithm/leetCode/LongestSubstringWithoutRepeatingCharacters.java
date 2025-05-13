package algorithm.leetCode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();

        int left = 0;
        int maxLen = 0;

        // 전체 문자열을 순회
        for (int right = 0; right < s.length(); right++) {

            // set 에는 문자열의 단일 캐릭터셋만 존재함
            while (set.contains(s.charAt(right))) {

                // 만일 set 에 단일 캐릭터 셋이 존재한다면 해당 캐릭터를 제거
                set.remove(s.charAt(left));

                // 포인트 증가
                left ++;
            }

            // 현재 캐릭터 추가
            set.add(s.charAt(right));

            // 최대값 갱신
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters ls = new LongestSubstringWithoutRepeatingCharacters();
        String ss = "abcabcbb";
        int i1 = ls.lengthOfLongestSubstring(ss);
        System.out.println(i1);
    }
}
