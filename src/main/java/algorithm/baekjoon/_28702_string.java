package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _28702_string {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();

        // s3 가 숫자면
        if (isNumber(s3)) {
            int target = Integer.parseInt(s3) + 1;

            soutNumber(target, String.valueOf(target));
            return;
        }

        // 숫자가 아니면
        if (s3.equals("FizzBuzz")) {
            int i = Integer.parseInt(s2);
            System.out.println(i + 2);
            return;
        }

        if (s3.equals("Fizz")) {

            if (s2.equals("Buzz")) {
                int s1Num = Integer.parseInt(s1);
                System.out.println(s1Num + 3);
                return;
            }

            if (isNumber(s2)) {
                int i = Integer.parseInt(s2);
                int target = i + 2;
                soutNumber(target, String.valueOf(target));

                return;
            }
        }

        if (s3.equals("Buzz")) {

            if (s2.equals("Fizz")) {
                int s1Num = Integer.parseInt(s1);
                System.out.println(s1Num + 3);
                return;
            }

            if (isNumber(s2)) {
                System.out.println("Fizz");
                return;
            }
        }

    }

    private static void soutNumber(int target, String s3) {
        if (target % 3 == 0 && target % 5 == 0) {
            System.out.println("FizzBuzz");
            return;
        }

        if (target % 3 == 0 && target % 5 != 0) {
            System.out.println("Fizz");
            return;
        }

        if (target % 3 != 0 && target % 5 == 0) {
            System.out.println("Buzz");
            return;
        }

        System.out.println(s3);
        return;
    }

    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
