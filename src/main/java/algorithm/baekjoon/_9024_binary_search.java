package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _9024_binary_search {

    static int t;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while (t > 0) {
            List<Integer> plusLists = new ArrayList<>();
            List<Integer> minusLists = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            StringTokenizer st1 = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                int data = Integer.parseInt(st1.nextToken());
                if (data >= 0) {
                    plusLists.add(data);
                } else {
                    minusLists.add(data);
                }
            }

            // 플러스 오름차순
            Collections.sort(plusLists);
            // 마이너스 내림차순
            Collections.sort(minusLists);

            int left = 0;
            int right = k - 1;




            t -= 1;
        }
    }
}
