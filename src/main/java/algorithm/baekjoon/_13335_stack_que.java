package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// https://www.acmicpc.net/problem/13335
public class _13335_stack_que {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int w = Integer.parseInt(s[1]);
        int l = Integer.parseInt(s[2]);

        int answer = 0;

        Deque<Integer> bridge = new LinkedList<>();

        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }

        int idx = 0;
        int currentWeight = 0;

        while (idx < w) {
            currentWeight -= bridge.poll();
            answer ++;

//            if (currentWeight + )
        }
    }
}
