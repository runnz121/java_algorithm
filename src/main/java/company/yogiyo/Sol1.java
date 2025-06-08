package company.yogiyo;

public class Sol1 {

    static String DASH = "-";

    public boolean solution(String s) {

        if (s == null) return false;

        String[] splitNumbers = s.split(DASH);

        // 3개의 구분 확인
        if (splitNumbers.length != 3) {
            return false;
        }

        // 각 번호 길이 확인
        for (String segment : splitNumbers) {
            if (segment.length() != 3) {
                return false;
            }
            // 모두 숫자인지 확인
            for (int idx = 0; idx < segment.length(); idx++) {
                if (Character.isDigit(segment.charAt(idx)) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
        s1.solution("!23-123-123");
    }
}
