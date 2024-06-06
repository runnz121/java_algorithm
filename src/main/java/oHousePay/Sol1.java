package oHousePay;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sol1 {

    public int solution(String s) {
        int answer = 0;
        int[][] keyPad = new int[9][3];

        String[] split = s.split("");

        for (int i = 0; i < split.length; i++) {

            String aWord = split[i];

            for (int a = 0; a < 10; a++) {

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Sol1 sol1 = new Sol1();
        String input = "ohouse";
        sol1.solution(input);
    }
}
