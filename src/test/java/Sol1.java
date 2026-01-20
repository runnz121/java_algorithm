import org.junit.Test;

public class Sol1 {

    public int solution(String[] strs) {

        int ans = 0;
        for (String s : strs) {
            matchFound(s);
        }

        return ans;
    }

    // (AAB+ | BAB+A)+  를 판별
    public boolean matchFound(String s) {

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {

            if (dp[i] == false) {
                continue;
            }

            // AAB+ 를 찾기
            if (i + 2 <= n && s.charAt(i) == 'A' && s.charAt(i + 1) == 'A') {
                int k = i + 2;
                // B+ 를 찾기
                while (k < n && s.charAt(k) == 'B') {

                    dp[k + 1] = true;
                    k ++;
                }
            }

            // BAB+A
            if (i + 2 <= n && s.charAt(i) == 'B' && s.charAt(i + 1) == 'A') {

                int k = i +2;

                while (k < n && s.charAt(k + 1) =='B') {
                    if (k + 1 < n && s.charAt(k + 1) == 'A') {
                        dp[k + 2] = true;
                    }
                    k++;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {


    }
}
