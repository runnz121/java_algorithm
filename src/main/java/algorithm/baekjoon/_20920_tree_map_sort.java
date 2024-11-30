package algorithm.baekjoon;

import java.io.*;
import java.util.*;

public class _20920_tree_map_sort {

    static int N, M;
    static Map<String, Integer> dict = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if (s.length() >= M) {
                dict.put(s, dict.getOrDefault(s, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> toMapList = new ArrayList<>(dict.entrySet());

        toMapList.sort((v1, v2) -> {
            // 1. value 기준으로 정렬
            int valc = v2.getValue().compareTo(v1.getValue());
            if (valc != 0) {
                return valc;
            }
            // 2. key 길이기준으로 정렬
            int lenC = Integer.compare(v2.getKey().length(), v1.getKey().length());
            if (lenC != 0) {
                return lenC;
            }
            // 3. 사전순으로 정렬
            return v1.getKey().compareTo(v2.getKey());
        });

        for (Map.Entry<String, Integer> entry : toMapList) {
            String word = entry.getKey();
            bw.write(String.valueOf(word));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
