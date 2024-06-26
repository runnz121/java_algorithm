package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2393_input_output {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder str = new StringBuilder();

        str.append("  ___  ___  ___\n" +
                "  | |__| |__| |\n" +
                "  |           |\n" +
                "   \\_________/\n" +
                "    \\_______/\n" +
                "     |     |\n" +
                "     |     |\n" +
                "     |     |\n" +
                "     |     |\n" +
                "     |_____|\n" +
                "  __/       \\__\n" +
                " /             \\\n" +
                "/_______________\\");

        System.out.print(str);
        br.close();
    }
}
