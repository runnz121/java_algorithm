package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14002_dp {

    static int N;
    static int[] LISTS;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        LISTS = new int[N];
        for(int i = 0; i < N; i++) {
            LISTS[i] = Integer.parseInt(st.nextToken());
        }
    }
}
