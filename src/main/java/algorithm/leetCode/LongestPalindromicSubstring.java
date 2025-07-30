package algorithm.leetCode;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        int n = s.length();

        if (n < 2) {
            return s;
        }

        char[] cs = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int bestStart = 0, bestLen = 1;

        // end 를 0부터 n-1까지 늘려가며
        for (int end = 0; end < n; end++) {

            dp[end][end] = true;  // 길이 1 substrings

            for (int start = 0; start < end; start++) {

                // 양끝이 같고, 사이가 팰린드롬이거나 길이가 ≤2 라면
                if (cs[start] == cs[end] && (end - start < 2 || dp[start + 1][end - 1])) {

                    dp[start][end] = true;
                    int len = end - start + 1;

                    if (len > bestLen) {
                        bestLen = len;
                        bestStart = start;
                    }
                }
            }
        }

        return s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring ps = new LongestPalindromicSubstring();
        String s = "babad";
        String s1 = ps.longestPalindrome(s);
        System.out.println(s1);
    }
}
