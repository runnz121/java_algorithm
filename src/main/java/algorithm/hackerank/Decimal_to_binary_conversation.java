package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Decimal_to_binary_conversation {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String input = br.readLine();

            if (input == null || input.isEmpty()) {
                break;
            }

            input = input.trim();
            Long inputLong = Long.parseLong(input);
            int toInt = inputLong.intValue();
            String bString = Integer.toBinaryString(toInt);

            int leftLength = 32 - bString.length();

            String answer = "";
            for (int i = 0; i < leftLength; i++) {
                answer += "0";
            }

            System.out.println(answer + bString);
        }

        br.close();
    }
}
