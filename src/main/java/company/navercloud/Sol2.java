package company.navercloud;


//A string is a palindrome if it reads the same backward as forward.
//        For example, "madam" and "racecar" are palindromes, but "milk" is not.
//
//        Write a function:
//
//class Solution { public int solution(String S); }
//that, given a string S made of N letters, returns the maximum number of three-letter palindromes you can build using letters from S.
//        You can use each letter from S once.
//
//        Examples
//
//        Given S = "aaaabc", the function should return 2.
//
//        Examples of three-letter palindromes you can build simultaneously are "aba" and "aca".
//
//        Given S = "xywzwy", the function should return 1.
//
//        Given S = "dd", the function should return 0.
//
//        You cannot build any three-letter palindrome.
//
//        Given S = "fknfkn", the function should return 2.
//
//        조건
//
//        N is an integer within the range [1..50,000].
//
//        S is made only of lowercase letters (a-z).
public class Sol2 {

    public int solution(String S) {
        int n = S.length();
        // 1) 각 문자별 개수 세기
        int[] freq = new int[26];
        for (char c : S.toCharArray()) {
            freq[c - 'a']++;
        }
        // 2) 사용할 수 있는 “쌍”(끝‐문자 두 개) 개수 합산
        int pairs = 0;
        for (int f : freq) {
            pairs += f / 2;
        }
        // 3) 3글자짜리 팰린드롬을 k개 만들려면
        //    - 쌍이 k개 있어야 하고
        //    - 전체 문자 수가 3k 이상이어야 한다
        // 따라서 정답은 min(pairs, n/3)
        return Math.min(pairs, n / 3);
    }
}
