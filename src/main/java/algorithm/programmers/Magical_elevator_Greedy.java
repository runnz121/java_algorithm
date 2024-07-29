package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/148653
public class Magical_elevator_Greedy {

    public int solution(int storey) {
        int answer = 0;

        while (storey != 0) {
            // 1의 자리
            int one = storey % 10;
            // 10의 자리
            int ten = (storey / 10) % 10;
            // 1의 자리가 5보다 크면 올림
            if (one > 5) {
                answer += (10 - one);
                storey += 10;
            // 10의 자리가 5이상이면 올림, 5 미만이면 내림
            } else if (one == 5) {
                answer += one;
                storey += (ten < 5 ? 0 : 10);
            } else {
                answer += one;
            }
            storey /= 10;
        }
        return answer;
    }

    public static void main(String[] args) {

        Magical_elevator_Greedy me = new Magical_elevator_Greedy();
        me.solution(2554);
    }
}
