package algorithm.programmers;

import java.util.HashMap;
import java.util.Map;

public class Discount_days {

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        int totalCnt = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            map.put(want[i], number[i]);
            totalCnt += number[i];
        }
        //
        for (int idx = 0; idx < discount.length - totalCnt; idx++) {
            for (int x = idx; x < totalCnt; x++) {

            }
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
