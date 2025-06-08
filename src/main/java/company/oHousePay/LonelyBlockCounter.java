package company.oHousePay;

import java.util.LinkedList;
import java.util.Queue;

public class LonelyBlockCounter {

    public static void main(String[] args) {
        String[][] arr1 = {{"W", "W", "B"}, {"W", "B", "W"}, {"B", "W", "W"}};
        String[][] inputs2 = {{"B","B","W"},{"B","B","W"},{"B","B","W"}};
        System.out.println("Lonely 'B' count in arr1: " + countLonelyBs(inputs2));
    }

    private static int countLonelyBs(String[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int lonelyBCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j].equals("B") && isLonelyBFS(arr, i, j, m, n)) {
                    lonelyBCount++;
                }
            }
        }

        return lonelyBCount;
    }

    private static boolean isLonelyBFS(String[][] arr, int startRow, int startCol, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visitedRow = new boolean[m];
        boolean[] visitedCol = new boolean[n];

        // Add initial positions for BFS: all cells in the row and column
        for (int i = 0; i < m; i++) {
            if (i != startRow) {
                queue.add(new int[]{i, startCol});
                visitedRow[i] = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (j != startCol) {
                queue.add(new int[]{startRow, j});
                visitedCol[j] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (arr[row][col].equals("B")) {
                return false;
            }
        }

        return true;
    }
}
