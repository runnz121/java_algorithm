package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _12605_stack {

    private static final String PREFIX = "Case #%s: %s";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        Integer n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            List<String> strings = new ArrayList<>();
            StringTokenizer st1 =  new StringTokenizer(br.readLine(), " ");
            while (st1.hasMoreTokens()) {
                strings.add(st1.nextToken());
            }

            Collections.reverse(strings);

            String res = String.format(PREFIX, i + 1, String.join(" ", strings));
            System.out.println(res);
        }
    }
}
