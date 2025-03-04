package algorithm.programmers.pccp;

public class Sol2 {

    public String solution(String input_string) {
        int[] segments = new int[26];
        char[] chars = input_string.toCharArray();

        segments[chars[0] - 'a']++;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[i - 1]) {
                segments[chars[i] - 'a']++;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (segments[i] >= 2) {
                result.append((char)('a' + i));
            }
        }

        return result.length() > 0 ? result.toString() : "N";
    }

    public static void main(String[] args) {
        Sol2 s2 = new Sol2();
        String tt = "edeaaabbccd";
        s2.solution(tt);
    }
}
