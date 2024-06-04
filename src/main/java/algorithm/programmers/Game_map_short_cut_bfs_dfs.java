package algorithm.programmers;

public class Game_map_short_cut_bfs_dfs {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int M;

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;

        int[][] arr = new int[N][M];

        bfs();

    }

    private void bfs() {

    }

    public static void main(String[] args) {
        Game_map_short_cut_bfs_dfs bms = new Game_map_short_cut_bfs_dfs();
        int[][] inputs = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        bms.solution(inputs);
    }
}
