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

        // 스왑을 하지 않았을 때의 최대값으로 초기화
        answer = Math.max(checkMaxRow(), checkMaxColumn());

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                char std = maps[y][x];

                for (int d = 0; d < 4; d++) {

                    int newY = y + dy[d];
                    int newX = x + dx[d];

                    // 전역 n(보드 크기)로 경계 체크
                    if (newY < 0 || newX < 0 || newX >= n || newY >= n) {
                        continue;
                    }

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

    // 열 기준 확인
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

    // 행 기준 확인
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
