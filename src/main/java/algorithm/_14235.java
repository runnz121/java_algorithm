package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _14235 {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            // 거점지가 아님
            if (x == 0) {
                if (!maxheap.isEmpty()) {
                    Integer out = maxheap.poll();
                    bw.write(String.valueOf(out));
                    bw.newLine();
                    bw.flush();
                } else {
                    bw.write(String.valueOf(-1));
                    bw.newLine();
                    bw.flush();
                }
            } else {
                for (int k = 0; k < x; k++) {
                    int y = Integer.parseInt(st.nextToken());
                    maxheap.add(y);
                }
            }
        }
    }
}
