package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinaryNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String toBString = "";


        // == Integer.toBinaryString() 사용해도된다.
        while (n > 0) {
            Integer mod = n % 2;
            n /= 2;
            toBString += String.valueOf(mod);
        }

        String[] ss = toBString.split("");

        int answer = 0;
        int mid = 0;
        for (int i = 0; i< toBString.length(); i++) {

            int current = Integer.parseInt(ss[i]);
            if (current == 1) {
                mid += 1;
            }
            else {
                answer = Math.max(answer, mid);
                mid = 0;
            }
        }

        if (mid != 0) {
            answer = Math.max(answer, mid);
        }

        System.out.println(answer);
        bufferedReader.close();
    }
}
