package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _11811_bitMask {

    static int N;
    static int [] p;

    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        p = new int[1001];

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st1 =  new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                int k = Integer.parseInt(st1.nextToken());
                p[i] = p[i] | k;
            }
            results.add(p[i]);
        }
        results.forEach(it -> System.out.print(it + " "));
    }
}
