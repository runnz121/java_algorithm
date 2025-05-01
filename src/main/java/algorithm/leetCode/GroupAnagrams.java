package algorithm.leetCode;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }

            map.get(sortedStr).add(s);
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();
        String[] s = {"eat","tea","tan","ate","nat","bat"};
        ga.groupAnagrams(s);
    }
}
