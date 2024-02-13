package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _22864 {

    static int[] arr;
    static int tired, A, B, C, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int x =  Integer.parseInt(st.nextToken());
            arr[i] = x;
        }
        tired = 0;
        A = arr[0];
        B = arr[1];
        C = arr[2];
        M = arr[3];
        solve(0,0,0);
        System.out.println(tired);
    }

    static void solve(int t, int a, int s) {
        if(t == 24) {
            tired = Math.max(tired, s);
            return;
        }
        if(a + A <= M) {
            solve(t + 1, a + A, s + B);
        }
        solve(t + 1, Math.max(0, a - C), s);
    }
}
