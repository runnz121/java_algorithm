package algorithm.programmers;

public class Expect_League {


    public int solution(int n, int a, int b) {

        int answer = 0;

        while (true) {
            a = a / 2 + a % 2;
            b = b / 2 + b % 2;
            answer += 1;
            if (a == b) {
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

        Expect_League el = new Expect_League();
        el.solution(8, 4, 7);
    }
}
