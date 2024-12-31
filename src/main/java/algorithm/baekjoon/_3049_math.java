package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _3049_math {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());

        if (n < 4) {
            System.out.println(0);
            return;
        }

        int answer = n;
        for (int i = 1; i < 4; i++) {
            answer *= (n - i);
        }

        System.out.println(answer/24);
    }
}
