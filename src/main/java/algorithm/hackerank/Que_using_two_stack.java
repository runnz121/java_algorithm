package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Que_using_two_stack {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        Deque<Integer> que = new ArrayDeque<>();
        while (T != 0) {

            String[] s = br.readLine().trim().split("\\s+");

            if (s.length == 2) {
                que.add(Integer.parseInt(s[1]));
            }

            else if (Integer.parseInt(s[0]) == 2) {
                if (que.isEmpty() == false) {
                    que.pollFirst();
                }
            }
            else {
                if (que.isEmpty() == false) {
                    System.out.println(que.peekFirst());
                }
            }

            T --;
        }
    }
}
