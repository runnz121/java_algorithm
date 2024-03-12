package algorithm.baekjoon;

import java.util.Scanner;

public class _11811_bitMask {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] p = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = scanner.nextInt();
                // 대각선 값은 무시
                if (i == j) continue;
                // 특정 숫자가 켜져있다 == or 연산을 진행하면 나의 최소한 켜져있어야 하는 1을 알 수 있다. 
                p[i] |= k;
            }
        }
        for (int i = 0; i < N; i++)
            System.out.print(p[i] + " ");
        scanner.close();
    }
}
