package algorithm.programmers;

import java.util.*;

public class Choose_tangerines {

    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Long> tangerineMap = new HashMap<>();
        for (int tan : tangerine) {
            tangerineMap.put(tan, tangerineMap.getOrDefault(tan, 0L) + 1);
        }

        Map<Integer, Long> reverseOrderedMap = new LinkedHashMap<>();
        tangerineMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .forEachOrdered(x -> reverseOrderedMap.put(x.getKey(), x.getValue()));

        System.out.println(reverseOrderedMap);

        int count = 0;
        Long sums = 0L;
        for (Map.Entry<Integer, Long> entry : reverseOrderedMap.entrySet()) {
            if (entry.getValue() + sums >= k) {
                count ++;
                break;
            } else {
                sums += entry.getValue();
                count ++;
            }
        }

        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        Choose_tangerines ct = new Choose_tangerines();
        int k = 2;
        int[] input = {1, 1, 1, 1, 2, 2, 2, 3};
        ct.solution(k, input);
    }
}
