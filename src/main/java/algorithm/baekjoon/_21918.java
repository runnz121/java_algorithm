package algorithm.baekjoon;

import java.util.*;
import java.lang.*;
import java.io.*;

public class _21918 {
    static int []state;
    public static void main(String[] args) {
        Reader rd = new Reader();
        int N = rd.nextInt();
        int M = rd.nextInt();

        // new 로 생성하면 상태 기본값으로 생성됨
        state = new int[N + 1];

        for (int i = 1; i <= N; i ++) {
            state[i] = rd.nextInt();
        }

        for (int i = 0; i < M; i++) {
            int command = rd.nextInt();
            int first = rd.nextInt();
            int second = rd.nextInt();
            // commad = 1 인 경우
            if (command == 1) {
                state[first] = second;
            } else if(command == 2) {
                for (int j = first; j <= second; j++) {
                    // 해당 인덱스 값이 1가 같으면 0 아니면 그대로
                    state[j] ^= 1;
                }
            } else if(command == 3) {
                for (int j = first; j <= second; j++) {
                    state[j] = 0;
                }
            } else {
                for (int j = first; j <= second; j++) {
                    state[j] = 1;
                }
            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            out.append(state[i] + " ");
        }
        System.out.println(out);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return str;
        }
    }
}
