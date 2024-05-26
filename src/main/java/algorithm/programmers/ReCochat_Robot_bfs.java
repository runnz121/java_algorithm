package algorithm.programmers;

import java.util.LinkedList;
import java.util.Queue;

public class ReCochat_Robot_bfs {

	public int solution(String[] board) {

		int answer = -1;
		int [] dx = {-1, 1, 0, 0};
		int [] dy = {0, 0, -1, 1};

		int N = board.length;
		int M = board[0].length();

		int [][] countGraph = new int[N][M];
		char[][] graph = new char[N][M];

		Current current = null;

		for (int i = 0; i < N; i++) {
			char[] split = board[i].toCharArray();
			for (int j = 0; j < M; j++) {
				graph[i][j] = split[j];
				// 로봇 시작위치
				if (split[j] == 'R') {
					current = new Current(i, j);
					// 시작 위치는 + 1 해줌
					countGraph[i][j] = 1;
				}
			}
		}

		// bfs
		Queue<Current> que = new LinkedList<>();
		que.offer(current);

		while (!que.isEmpty()) {
			Current pop = que.poll();
			int a = pop.x;
			int b = pop.y;

			// 목적지 도착인 경우
			if (graph[a][b] == 'G') {
				answer = countGraph[a][b] - 1;
				break;
			}

			for (int k = 0; k < 4; k++) {
				int nx = a + dx[k];
				int ny = b + dy[k];
				// 최대 지점 이동 (증감 기준으로 끝까지 확인)
				while (true) {
					// 범위 안인 경우 + 장애물 아닌 경우
					if (0 <= nx && nx < N && 0 <= ny && ny < M && graph[nx][ny] != 'D') {
						nx += dx[k];
						ny += dy[k];
					} else {
						nx -= dx[k];
						ny -= dy[k];
						break;
					}
				}
				if (countGraph[nx][ny] == 0) {
					countGraph[nx][ny] = countGraph[a][b] + 1;
					Current update = new Current(nx, ny);
					que.offer(update);
				}
			}
		}
		return answer;
	}

	private static class Current {

		int x;
		int y;

		Current(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		ReCochat_Robot_bfs bfs = new ReCochat_Robot_bfs();
		bfs.solution(new String[] {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."});
	}
}
