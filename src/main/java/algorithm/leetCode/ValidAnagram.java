package algorithm.leetCode;

import java.util.*;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        String[] s1 = s.split("");
        String[] t1 = t.split("");

        Map<String, Integer> s1Map = new TreeMap<>();
        Map<String, Integer> t1Map = new TreeMap<>();

        Arrays.sort(s1);
        Arrays.sort(t1);

        for (int i = 0; i < s1.length; i++) {
            s1Map.put(s1[i], s1Map.getOrDefault(s1[i], 0) + 1);
        }

        for (int i = 0; i < t1.length; i++) {
            t1Map.put(t1[i], t1Map.getOrDefault(t1[i], 0) + 1);
        }

        for (Map.Entry<String, Integer> sMap : s1Map.entrySet()) {
            String key = sMap.getKey();
            Integer t1Value = t1Map.get(key);

            if (!Objects.equals(t1Value, sMap.getValue())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidAnagram va = new ValidAnagram();
        boolean anagram = va.isAnagram("rat", "car");
        System.out.println(anagram);
    }
}
