package algorithm.programmers;

import java.util.Arrays;

public class Life_boat_greedy {

    public int solution(int[] people, int limit) {
        int answer = 0;
        // 정렬
        Arrays.sort(people);

        int min = 0;
        int max = people.length - 1;

        for (int i = max; min <= max; max--) {
            // 제일 무거운 사람과 가벼운 사람이 제한 무게 아래인지 확인
            if (people[min] + people[i] <= limit) {
                min++;
            }
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {

        Life_boat_greedy boatGreedy = new Life_boat_greedy();
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        boatGreedy.solution(people, limit);
    }
}
