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
                if (i == j) continue;
                p[i] |= k;
            }
        }
        for (int i = 0; i < N; i++)
            System.out.print(p[i] + " ");
        scanner.close();
    }
}
