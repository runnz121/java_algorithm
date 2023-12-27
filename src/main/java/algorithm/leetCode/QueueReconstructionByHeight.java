package algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueReconstructionByHeight {

	public int[][] reconstructQueue(int[][] people) {

		// 우선수위 큐 선언 정렬 기준은 킨 우선, 값이 같다면 앞에 줄 서 있는 사람이 작은 순으로
		Queue<int[]> pq = new PriorityQueue<>(
			(a, b) -> a[0] != b[0] ?
				b[0] - a[0] : a[1] - b[1]

			// b[0] - a[0] 이 양수인 경우 -> b[0] 이 우선순위 = b[0] > a[0]
			// b[0] - a[0] 이 음수인 경우 -> a[0] 이 우선순위 = a[0] > b[0]
		);

		for (int[] person : people) {
			pq.add(person);
		}

		List<int[]> res = new ArrayList<>();

		while (!pq.isEmpty()) {
			// 큰 키 우선, 앞에 줄 서 있는 사람이 작은 순으로 추출
			int[] person = pq.poll();

			// 앞에 줄 서 있는 사람을 인덱스로 정해서 삽입
			res.add(person[1], person);
		}

		return res.toArray(new int[people.length][2]);
	}

	public static void main(String[] args) {
		QueueReconstructionByHeight rs = new QueueReconstructionByHeight();
		int[][] sample = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
		int[][] res = rs.reconstructQueue(sample);
		System.out.println(res);
	}
}
