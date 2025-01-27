package swdx;

import java.util.ArrayList;

class Sol1 {

    private final static int MAX_N	= 1000;
    private final static int MAX_M	= 20;

    private int N, M;
    private boolean[][] used;
    private ArrayList<int[]> treasureList;


    public void init(int N, int M, int Map[][]) {
        this.N = N;
        this.M = M;

        used = new boolean[N][N];
        treasureList = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (Map[y][x] == 1) {
                    used[y][x] = true;
                    treasureList.add(new int[] { y, x });
                } else {
                    used[y][x] = false;
                }
            }
        }
    }

    public Solution1.Result findTreasureChest(int Pieces[][]){

        Solution1.Result res = new Solution1.Result();

        int py9 = -1, px9 = -1;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                if (Pieces[y][x] == 9) {
                    py9 = y;
                    px9 = x;
                    break;
                }
            }
            if (py9 != -1) break;
        }

        ArrayList<int[]> offsets0 = new ArrayList<>();
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                if (Pieces[y][x] == 1 || Pieces[y][x] == 9) {
                    offsets0.add(new int[] { y - py9, x - px9 });
                }
            }
        }

        ArrayList<int[]> offsets90  = rotateOffsets(offsets0, 90);
        ArrayList<int[]> offsets180 = rotateOffsets(offsets0, 180);
        ArrayList<int[]> offsets270 = rotateOffsets(offsets0, 270);

        if (matchAndReturn(offsets0, res))   return res;
        if (matchAndReturn(offsets90, res))  return res;
        if (matchAndReturn(offsets180, res)) return res;
        if (matchAndReturn(offsets270, res)) return res;

        res.y = res.x = 0;

        return res;
    }

    private boolean matchAndReturn(ArrayList<int[]> offsets, Solution1.Result res) {
        for (int[] t : treasureList) {
            int Y = t[0], X = t[1];
            if (!used[Y][X]) continue;

            boolean canMatch = true;
            for (int[] off : offsets) {
                int ny = Y + off[0];
                int nx = X + off[1];
                if (!inRange(ny, nx) || !used[ny][nx]) {
                    canMatch = false;
                    break;
                }
            }
            if (canMatch) {
                res.y = Y;
                res.x = X;
                for (int[] off : offsets) {
                    int ny = Y + off[0];
                    int nx = X + off[1];
                    used[ny][nx] = false;
                }
                return true;
            }
        }
        return false;
    }

    private ArrayList<int[]> rotateOffsets(ArrayList<int[]> original, int angle) {
        ArrayList<int[]> rotated = new ArrayList<>();
        for (int[] off : original) {
            int dy = off[0], dx = off[1];
            int rdy, rdx;
            switch(angle) {
                case 90:
                    rdy = dx;
                    rdx = -dy;
                    break;
                case 180:
                    rdy = -dy;
                    rdx = -dx;
                    break;
                case 270:
                    rdy = -dx;
                    rdx = dy;
                    break;
                default:
                    rdy = dy;
                    rdx = dx;
                    break;
            }
            rotated.add(new int[] { rdy, rdx });
        }
        return rotated;
    }

    private boolean inRange(int y, int x) {
        return (0 <= y && y < N && 0 <= x && x < N);
    }
}
