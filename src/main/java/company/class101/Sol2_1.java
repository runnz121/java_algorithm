package company.class101;

import java.util.HashMap;
import java.util.Map;

public class Sol2_1 {

    public long solution(String[] arr1, String[] arr2) {

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        // arr1 처리
        for (String s : arr1) {
            int[] info = getInfo(s); // [netDiff, minPrefix]
            int netDiff = info[0];
            int minPrefix = info[1];

            // arr1에서는 netDiff >=0, minPrefix>=0 인 경우만 카운트
            if (netDiff >= 0 && minPrefix >= 0) {
                map1.put(netDiff, map1.getOrDefault(netDiff, 0) + 1);
            }
        }

        // arr2 처리
        for (String s : arr2) {
            int[] info = getInfo(s);
            int netDiff = info[0];
            int minPrefix = info[1];

            // arr2:
            // netDiff=0인 경우 minPrefix>=0
            // netDiff<0인 경우 minPrefix>=netDiff
            if ((netDiff == 0 && minPrefix >= 0) || (netDiff < 0 && minPrefix >= netDiff)) {
                map2.put(netDiff, map2.getOrDefault(netDiff, 0) + 1);
            }
        }

        int count = 0;
        // map1의 key = d, map2의 key = -d 매칭
        for (Map.Entry<Integer, Integer> e : map1.entrySet()) {
            int d = e.getKey();
            int c1 = e.getValue();
            int c2 = map2.getOrDefault(-d, 0);
            count += c1 * c2;
        }

        System.out.println(count);
        return count;
    }

    // 문자열 s에 대해 netDiff와 minPrefix를 계산
    // netDiff = count('(') - count(')')
    // minPrefix = 스캔 중 balance의 최소값
    private static int[] getInfo(String s) {
        int balance = 0;
        int minPrefix = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else balance--;
            minPrefix = Math.min(minPrefix, balance);
        }
        // balance가 최종 netDiff
        return new int[]{balance, minPrefix};
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
