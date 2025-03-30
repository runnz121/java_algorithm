package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1520_dp_dfs {

	static int N;
	static int M;
	static int[][] map;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {0, -1, 0, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int k = Integer.parseInt(s[j]);
				map[i][j] = k;
			}
		}

		System.out.println(Arrays.deepToString(map));
	}

	static void checkMin(int x,
		int y) {

	}
}
