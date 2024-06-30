package algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/131127
public class Discount_days {

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        List<String> wants = new ArrayList<>();

        for (int i = 0; i < want.length; i++) {
            while (number[i]-- > 0) {
                wants.add(want[i]);
            }
        }

        for (int i = 0; i < discount.length - 10 + 1; i++) {
            List<String> buys = new ArrayList<>(wants);

            for (int j = i; j < 10 + i; j++) {
                if (!buys.contains(discount[j])) {
                    continue;
                }
                if (buys.contains(discount[j])) {
                    buys.remove(discount[j]);
                }
            }
            answer += buys.size() == 0 ? 1 : 0;
        }
        return answer;
    }

    public static void main(String[] args) {
        Discount_days dd = new Discount_days();
        String[] wants = {"banana", "apple", "rice", "pork", "pot"};
        int[] numbers = {3, 2, 2, 2, 1};
        String[] discounts = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        dd.solution(wants, numbers, discounts);
    }
}
