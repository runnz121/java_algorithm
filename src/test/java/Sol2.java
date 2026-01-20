import javax.imageio.ImageTranscoder;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Sol2 {

    public static class Node {

        int r;
        int c;
        int val;

        Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};

    public int solution(int n, int m, int[][] order) {

        final int INF = 987654321;

        int[][] tt = new int[n][m];
        for (int[] row : tt) {
            Arrays.fill(row, INF);
        }

        for (int i = 0; i <order.length; i++) {
            int r = order[i][0] - 1;
            int c = order[i][1] - 1;
            tt[r][c] = i + 1;
        }

        int[][] best = new int[n][m];
        for (int[] row : best) {
            Arrays.fill(row, -1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val));
        best[0][0] = INF;
        pq.add(new Node(0, 0, INF));

        while (pq.isEmpty() == false) {
            Node cur = pq.poll();
            int r = cur.r;
            int c = cur.c;
            int val = cur.val;

            if (val < best[r][c]) {
                continue;
            }

            if (r == n - 1 && c == m - 1) {
                return val;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                int nv = Math.min(val, tt[nr][nc]);

                if (nv > best[nr][nc]) {
                    best[nr][nc] = nv;
                    pq.add(new Node(nr, nc, nv));
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Sol2 s2 = new Sol2();
        int[][] arr = {
                {3, 2},
                {3, 1},
                {1, 4},
                {1, 2},
                {2, 4},
                {2, 3},
                {2, 2},
                {1, 3},
                {2, 1},
                {3, 3}
        };
        int solution = s2.solution(3, 4, arr);

        System.out.println(solution);
    }
}
