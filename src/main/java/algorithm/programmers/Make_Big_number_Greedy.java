package algorithm.programmers;

public class Make_Big_number_Greedy {
    public String solution(String number, int k) {
        int len = number.length();
        int targetLen = len - k;
        StringBuilder answer = new StringBuilder();

        int start = 0;
        for (int i = 0; i < targetLen; i++) {
            char maxDigit = '0';
            for (int j = start; j <= k + i; j++) {
                if (number.charAt(j) > maxDigit) {
                    maxDigit = number.charAt(j);
                    // 이미 지난 것보다 큰 경우 시작 인덱스를 갱신
                    start = j + 1;
                }
            }
            answer.append(maxDigit);
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Make_Big_number_Greedy greedy = new Make_Big_number_Greedy();
        greedy.solution("4177252841", 4);
        greedy.solution("1924", 2);
        greedy.solution("1231234", 3);

//        greedy.solution("99999999",  6);
    }
}
