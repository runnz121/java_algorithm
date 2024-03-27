package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _15666_backTracking {

    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    static int[] inputs;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inputs = new int[N];

        String[] inputLists = br.readLine().split(" ");
        for (int k = 0; k < inputLists.length; k++) {
            inputs[k] = Integer.parseInt(inputLists[k]);
        }

        backTracking(0);

        bw.write(String.valueOf(sb));
        bw.close();
    }

    private static void backTracking(int depth) {

        if (depth == M) {

            return;
        }

        for (int idx = 0; idx < inputs.length; idx++) {

        }
    }
}
