package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _11656_string {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        List<String> answer = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(i, s.length());
            answer.add(substring);
        }

        Collections.sort(answer);

        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }
}
