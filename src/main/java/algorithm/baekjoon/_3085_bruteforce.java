package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _3085_bruteforce {

    static int n;
    static char[][] maps;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        maps = new char[n][n];

        for (int y = 0; y < n; y++) {
            char[] charArray = br.readLine().toCharArray();
            for (int x = 0; x < n; x++) {
                maps[y][x] = charArray[x];
            }
        }

    }

    static void checkMaxRow() {

        Map<String, Integer> countMap = new HashMap<>();

        for (int y = 0; y < n; y++) {

            char std = maps[y][0];
            for (int x = 0; x < n; x++) {
                countMap.put();
            }
        }
    }
}
