package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _3085_bruteforce {

    static int n;
    static char[][] maps;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;

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

        char[][] copyMap = new char[n][n];
        for (int k = 0; k < n; k++) {
            copyMap[k] = maps[k].clone();
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                char std = maps[y][x];

                for (int n = 0; n < 4; n++) {

                    int newY = y + dy[n];
                    int newX = x + dx[n];

                    if (newY < 0 || newX < 0 || newX >= n || newY >= n) continue;

                    char comp = maps[newY][newX];
                    if (std != comp) {
                        // 스왑
                        maps[newY][newX] = std;
                        maps[y][x] = comp;

                        int maxColumnCount = checkMaxColumn();
                        int maxRowCount = checkMaxRow();
                        int max = Math.max(maxColumnCount, maxRowCount);
                        answer = Math.max(max, answer);

                        // 원복 (다시 스왑)
                        maps[y][x] = std;
                        maps[newY][newX] = comp;
                    }
                }
            }
        }

        System.out.println(answer);

    }

    static int checkMaxRow() {
        int maxCount = 1;

        for (int y = 0; y < n; y++) {
            int cnt = 1;
            for (int x = 1; x < n; x++) {
                if (maps[y][x] == maps[y][x - 1]) {
                    cnt++;
                }
                else {
                    cnt = 1;
                }
                if (cnt > maxCount) {
                    maxCount = cnt;
                }
            }
        }
        return maxCount;
    }

    static int checkMaxColumn() {
        int maxCount = 1;

        for (int x = 0; x < n; x++) {
            int cnt = 1;
            for (int y = 1; y < n; y++) {
                if (maps[y][x] == maps[y - 1][x]) {
                    cnt++;
                }
                else {
                    cnt = 1;
                }
                if (cnt > maxCount) {
                    maxCount = cnt;
                }
            }
        }
        return maxCount;
    }
}
