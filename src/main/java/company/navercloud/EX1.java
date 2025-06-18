package company.navercloud;

public class EX1 {

    public int solution(String s) {

        int n = s.length();

        if (n <= 1) {
            return 0;
        }

        int[] pi = new int[n];

        for (int i = 1; i < n; i++) {
            int x = pi[i - 1];

            while (x > 0 && s.charAt(i) != s.charAt(x)) {
                x = pi[x - 1];
            }
            if (s.charAt(x) == s.charAt(i)) {
                x ++;
            }
            pi[i] = x;
        }
        return pi[n - 1];
    }

    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
        int solution = s1.solution("abcabc");
        System.out.println(solution);
    }
}
