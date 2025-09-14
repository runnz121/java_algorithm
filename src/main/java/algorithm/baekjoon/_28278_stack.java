package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class _28278_stack {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Deque<Integer> stack = new ArrayDeque<>();

        while (n-- > 0) {

            String s = br.readLine();
            if (s == null) {
                 break;
            }

            if (s.length() > 1) {
                String[] split = s.split(" ");
                String s1 = split[1];
                stack.push(Integer.parseInt(s1));
                continue;
            }

            Integer x = Integer.parseInt(s);

            if (x == 2) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                }
                else {
                    System.out.println(stack.pop());
                }
                continue;
            }

            if (x == 3) {
                if (stack.isEmpty()) {
                    System.out.println(0);
                }
                else {
                    System.out.println(stack.size());
                }
                continue;
            }

            if (x == 4) {
                if (stack.isEmpty()) {
                    System.out.println(1);
                }
                else {
                    System.out.println(0);
                }
                continue;
            }

            if (x == 5) {
                if (stack.isEmpty()) {
                    System.out.println(-1);
                }
                else {
                    System.out.println(stack.peek());
                }
                continue;
            }
        }
    }
}
