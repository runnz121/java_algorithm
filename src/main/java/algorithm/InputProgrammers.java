package algorithm;

public class InputProgrammers {

    static class Solution {

        public static int solution(int num1, int num2) {
            int answer = 0;
            answer = num1 * num2;
            return answer;
        }

        public static void main(String[] args) {
            int res = solution(3, 4);
            System.out.println(res);
        }
    }

}
