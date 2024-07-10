package algorithm.programmers;

import java.util.Arrays;

public class Most_Big_Number_Sort {

    public String solution(int[] numbers) {

        String[] stringArr = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            stringArr[i] = String.valueOf(numbers[i]);
        }

        // 앞뒤 vs 뒤앞 문자열 비교 -> 큰 값을 기준으로 내림차순 정렬
        Arrays.sort(stringArr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        String result = String.join("", stringArr);

        // 처음이 0이면 모두  0
        if (result.charAt(0) == '0') {
            return "0";
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {

        Most_Big_Number_Sort mbns = new Most_Big_Number_Sort();
        int[] inputs = {6, 10, 2};
        mbns.solution(inputs);
    }
}
