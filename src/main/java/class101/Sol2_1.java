package class101;

import java.util.HashMap;
import java.util.Map;

public class Sol2_1 {

    public long solution(String[] arr1, String[] arr2) {

        Map<Integer, Integer> balanceMap1 = new HashMap<>();
        Map<Integer, Integer> balanceMap2 = new HashMap<>();

        for (String str : arr1) {
            int balance = 0;
            for (char c : str.toCharArray()) {
                if (c == '(') {
                    balance += 1;
                } else if (c == ')') {
                    balance -= 1;
                }
            }
            if (balance >= 0) {
                balanceMap1.put(balance, balanceMap1.getOrDefault(balance, 0) + 1);
            }
        }

        for (String str : arr2) {
            int balance = 0;
            for (char c : str.toCharArray()) {
                if (c == '(') {
                    balance += 1;
                } else if (c == ')') {
                    balance -= 1;
                }
            }
            if (balance <= 0) {
                balanceMap2.put(-balance, balanceMap2.getOrDefault(-balance, 0) + 1);
            }
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : balanceMap1.entrySet()) {
            int balance = entry.getKey();
            int freq1 = entry.getValue();

            if (balanceMap2.containsKey(balance)) {
                count += freq1 * balanceMap2.get(balance);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Sol2_1 s2 = new Sol2_1();
        String[] arr1 = {"()", "(()", ")()", "()"};
        String[] arr2 = {")()", "()", "(()"};
        s2.solution(arr1, arr2);

        String[] arr11 = {"()", "(()", "(", "(())"};
        String[] arr22 = {")()", "()", "(())", ")()"};
        s2.solution(arr11, arr22);
    }
}
