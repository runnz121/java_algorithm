package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _14626_bruteForce {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        String[] split = s.split("");

        int current = -1;
        int allSum = 0;
        for (int i = 0; i < split.length; i++) {

            String s1 = split[i];
            if (s1.equals("*")) {
               current = i;
               continue;
            }

            if ((i + 1) % 2 == 0) {
                allSum += Integer.parseInt(s1) * 3;
            } else {
                allSum += Integer.parseInt(s1);
            }
        }

        int multiply = 1;
        if ((current + 1) % 2 == 0) {
            multiply = 3;
        }

        for (int i = 0 ; i < 10; i++) {

            int cal = i * multiply;

            if ((allSum + cal) % 10 == 0) {
                System.out.println(i);
                return;
            }
        }

    }
}
