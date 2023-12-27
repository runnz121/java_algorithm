package coupang;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Sol3 {

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int N;
	static int M;

	public int solution(String[] maps) {

		int answer = 0;
		N = maps.length;
		M = maps[0].length();

		int[][] convertedMap = convertToArray(maps);

		for (int i = 0; i < N; i ++) {
			for (int p = 0; p < M; p++) {
				if (convertedMap[i][p] == 0) {
					convertedMap[i][p] = 2;
					int[][] changedMap = new int[N][M];
					for (int k = 0; k < N; k++) {
						System.arraycopy(convertedMap[k], 0, changedMap[k], 0, M);
					}
					for (int x = 0; x < N; x++) {
						for (int y = 0; y < M; y++) {
							int res = bfs(changedMap, x, y);
							answer = Math.max(res, answer);
						}
					}
					convertedMap[i][p] = 0;
				}
			}
		}

		return answer;
	}

	private int[][] convertToArray(String [] maps) {

		int rows = maps.length;
		int cols = maps[0].length();

		int[][] converted = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				converted[i][j] = maps[i].charAt(j) - '0';
			}
		}
		return converted;
	}

	static int bfs(int[][] map, int x, int y) {

		if (map[x][y] == 0) {
			return 0;
		}

		int answer = 0;

		if (map[0][0] == 1 || map[0][0] == 2) {
			answer = 1;
		}

		Queue<Cur> q = new LinkedList<>();
		Cur cur = new Cur(0,0);
		q.offer(cur);

		while(!q.isEmpty()) {
			Cur out = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = out.x + dx[i];
				int ny = out.y + dy[i];

				if ((0 <= nx) && (nx < N) && (0 <= ny) && (ny < M)) {
					if (map[nx][ny] == 1 || map[nx][ny] == 2) {
						answer += 1;
						Cur newCur = new Cur(nx, ny);
						q.offer(newCur);
						map[nx][ny] = -1;
					}
				}
			}
		}
		return answer;
	}

	static class Cur {
		int x;
		int y;

		Cur(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}

	public static void main(String[] args) {

		Sol3 s3 = new Sol3();
		String[] maps1 = {"111", "000", "000", "110"};
		String[] map = {"0000000111", "1110010110", "1110111200", "0000000111"};
		String[] maps2 = {"1000011", "1111000", "0000000", "1101111"};
		int res = s3.solution(map);
		System.out.println(res);
	}
}
