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

		boolean [][] isVisited = new boolean[N][M];
		int [][] countGraph = new int[N][M];
		char[][] graph = new char[N][M];

		Current current = null;

		for (int i = 0; i < board.length; i++) {
			char[] split = board[i].toCharArray();
			for (int j = 0; j < split.length; j++) {
				graph[i][j] = split[j];
				// 장애물
				if (split[j] == 'D') {
					isVisited[i][j] = true;
				}
				// 로봇 시작위치
				if (split[j] == 'R') {
					current = new Current(i, j);
					isVisited[i][j] = true;
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

			for (int k = 0; k < 4; k++) {
				int nx = a + dx[k];
				int ny = b + dy[k];

				// 범위 벗어 나는 경우
				if (0 > nx || 0 > ny || nx >= N || ny >= M) {
					continue;
				}
				// 방문 했는지 확인
				if (isVisited[nx][ny]) {
					continue;
				}
				// 목적지
				if (graph[nx][ny] == 'G') {
					System.out.println(countGraph[a][b]);
					return countGraph[a][b];
				}

				Current update = new Current(nx, ny);
				que.offer(update);
				isVisited[nx][ny] = true;
				countGraph[nx][ny] = countGraph[a][b] + 1;
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
