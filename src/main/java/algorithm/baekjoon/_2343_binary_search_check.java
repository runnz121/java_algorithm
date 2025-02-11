package algorithm.baekjoon;

import java.util.*;
import java.io.*;
public class _2343_binary_search_check {

    static int N;
    static int M;
    static List<Integer> lists = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");

        N = Integer.parseInt(line1[0]);
        M = Integer.parseInt(line1[1]);

        String[] line2 = br.readLine().split(" ");

        int left = 0;
        int right = 0;

        for (String line : line2) {
            Integer ele = Integer.parseInt(line);
            left = Math.max(ele, left);
            right += ele;
            lists.add(ele);
        }

        while (left <= right) {

            int total = 0;
            int mid = (left + right) / 2;
            int count = 0;

            for (int i = 0; i < N; i++) {
                if (total + lists.get(i) > mid) {
                    total = 0;
                    count += 1;
                }
                total += lists.get(i);
            }

            if (total > 0) {
                count += 1;
            }

            if (count <= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}
