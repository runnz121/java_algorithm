package algorithm.programmers;

public class Triangle_snail {
    public int[] solution(int n) {
        // 전체 길이
        int[] answer = new int[n * (n + 1) / 2];
        // 삼각형을 2차원 배열로 생각
        int[][] triangle = new int[n][n];

        int x = -1;
        int y = 0;
        int startValue = 1;
        for (int dx = 0; dx < n; dx++) {
            for (int dy = dx; dy < n; dy++) {
                // 아래 방향
                if (dx % 3 == 0) {
                    x += 1;
                }
                // 가로 방향
                else if (dx % 3 == 1) {
                    y += 1;
                }
                // 대각선 위 방향
                else if (dx % 3 == 2) {
                    x -= 1;
                    y -= 1;
                }
                triangle[x][y] = startValue++;
            }
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (triangle[i][j] == 0) break;
                answer[idx++] = triangle[i][j];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Triangle_snail ts = new Triangle_snail();
        ts.solution(4);
    }
}
