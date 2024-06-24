package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/42898
public class On_the_way_DP {

    public int solution(int m, int n, int[][] puddles) {
        int mod = 1000000007;
        // 웅덩이 초기화
        int[][] board = new int[n + 1][m + 1];
        for(int i = 0; i < puddles.length; i++) {
            board[puddles[i][1]][puddles[i][0]] = -1;
        }

        // 시작지점 초기화
        board[1][1] = 1;

        for (int i = 1; i < n + 1; i ++) {
            for (int k = 1; k < m + 1; k ++) {
                // 웅덩이 일 경우 패스
                if (board[i][k] == -1) {
                    continue;
                }
                // 위쪽값이 웅덩이가 아닌 경우 더해줌
                if (board[i - 1][k] > 0) {
                    board[i][k] += board[i - 1][k] % mod;
                }
                // 왼쪽값이 웅덩이가 아닌 경우 더해줌
                if (board[i][k - 1] > 0) {
                    board[i][k] += board[i][k - 1] % mod;
                }
            }
        }
        return board[n][m] % mod;
    }
    public static void main(String[] args) {

        On_the_way_DP onTheWayDp = new On_the_way_DP();
        int[][] puddles = {{2,2}};
        onTheWayDp.solution(4, 3, puddles);
    }
}
