package company.swdx;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Sol2
{
    private int W, H;
    private int[][] board;
    private int scoreP1, scoreP2;

    private final int[][] DIRS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1,-1}, {-1,1}, {1,-1}, {1,1}
    };

    void init(int W, int H)
    {
        this.W = W;
        this.H = H;
        board = new int[H][W];
        for(int r = 0; r < H; r++){
            for(int c = 0; c < W; c++){
                board[r][c] = 0;
            }
        }
        scoreP1 = 0;
        scoreP2 = 0;
    }

    int dropBlocks(int mPlayer, int mCol) {
        for (int col = mCol; col < mCol + 3; col++){
            for (int row = H - 1; row >= 0; row--){
                if (board[row][col] == 0){
                    board[row][col] = mPlayer;
                    break;
                }
            }
        }

        int turnScore = removeAndGetScore(mPlayer);
        if (mPlayer == 1) {
            scoreP1 += turnScore;
        } else {
            scoreP2 += turnScore;
        }

        return turnScore;
    }

    int changeBlocks(int mPlayer, int mCol) {
        int rowFound = -1;
        for(int row = H-1; row >= 0; row--){
            if(board[row][mCol] != 0){
                rowFound = row;
                break;
            }
        }
        if(rowFound != -1) {
            int foundColor = board[rowFound][mCol];
            if(foundColor != mPlayer) {
                convertConnected(rowFound, mCol, foundColor, mPlayer);
            }
        }

        int turnScore = removeAndGetScore(mPlayer);
        if (mPlayer == 1) {
            scoreP1 += turnScore;
        } else {
            scoreP2 += turnScore;
        }

        return turnScore;
    }

    private void convertConnected(int sr, int sc, int oldColor, int newColor){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});
        board[sr][sc] = newColor;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            for(int[] d : DIRS){
                if(d[0] != 0 && d[1] != 0) continue;

                int nr = r + d[0], nc = c + d[1];
                if(!inRange(nr, nc)) continue;
                if(board[nr][nc] == oldColor){
                    board[nr][nc] = newColor;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    private int removeAndGetScore(int mPlayer){
        int totalRemovedMyColor = 0;

        while(true){
            boolean[][] remove = new boolean[H][W];
            int removeCount = 0;


            for(int r = 0; r < H; r++){
                for(int c = 0; c < W; c++){
                    if(board[r][c] == 0) continue;
                    int color = board[r][c];

                    checkLine(r, c,  0, 1, color, remove);
                    checkLine(r, c,  1, 0, color, remove);
                    checkLine(r, c,  1, 1, color, remove);
                    checkLine(r, c,  1, -1, color, remove);
                }
            }

            int removedP1 = 0;
            int removedP2 = 0;

            for(int r=0; r<H; r++){
                for(int c=0; c<W; c++){
                    if(remove[r][c]){
                        removeCount++;
                        if(board[r][c] == 1) removedP1++;
                        else if(board[r][c] == 2) removedP2++;
                        board[r][c] = 0; // 제거
                    }
                }
            }

            if(removeCount == 0) break;

            if(mPlayer == 1) {
                totalRemovedMyColor += removedP1;
            }
            else {
                totalRemovedMyColor += removedP2;
            }

            // 중력으로 블록 떨어뜨리기
            applyGravity();
        }

        return totalRemovedMyColor;
    }


    private void checkLine(int r, int c, int dr, int dc, int color, boolean[][] remove){
        int nr = r, nc = c;
        int count = 0;

        ArrayList<int[]> cells = new ArrayList<>();
        while(inRange(nr, nc) && board[nr][nc] == color){
            cells.add(new int[]{nr, nc});
            count++;
            nr += dr;
            nc += dc;
        }

        if(count >= 5){
            for(int[] pos : cells){
                remove[pos[0]][pos[1]] = true;
            }
        }
    }

    private void applyGravity(){
        for (int c = 0; c < W; c++){
            int writePos = H - 1;
            for (int r = H - 1; r >= 0; r--){
                if (board[r][c] != 0){
                    int temp = board[r][c];
                    board[r][c] = 0;
                    board[writePos][c] = temp;
                    writePos--;
                }
            }
        }
    }

    private boolean inRange(int r, int c){
        return (0 <= r && r < H && 0 <= c && c < W);
    }

    int getResult(int[] mBlockCnt) {
        int cnt1 = 0, cnt2 = 0;
        for(int r = 0; r < H; r++){
            for(int c = 0; c < W; c++){
                if(board[r][c] == 1) cnt1++;
                else if(board[r][c] == 2) cnt2++;
            }
        }
        mBlockCnt[0] = cnt1;
        mBlockCnt[1] = cnt2;

        if(scoreP1 > scoreP2) return 1;
        else if(scoreP2 > scoreP1) return 2;
        else return 0;
    }
}