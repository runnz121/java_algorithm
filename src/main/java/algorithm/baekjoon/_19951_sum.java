package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19951
public class _19951_sum {

	static int N;
	static int M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		int[] area = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 땅 변화량을 누적 저장할 배열
		int[] sumArea = new int[area.length + 1];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());

			// 변화량 시작
			sumArea[a] += k;
			// 변화량 끝
			sumArea[b + 1] -= k;
		}

		// 높이 변화량을 누적합으로 적용시킴 (모든 변화량을 누적해 저장해놓고 나중에 값만 더한다)
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < sumArea.length - 1; x++) {
			sumArea[x + 1] = sumArea[x] + sumArea[x + 1];
			sb.append(sumArea[x] + area[x] + " ");
		}
		System.out.println(sb);
	}
}
