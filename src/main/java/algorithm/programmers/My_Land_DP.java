package algorithm.programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12913
public class My_Land_DP {

	int solution(int[][] land) {

		for(int i = 1; i < land.length; i++) {
			land[i][0] += Math.max(land[i-1][1], Math.max(land[i-1][2], land[i-1][3]));
			land[i][1] += Math.max(land[i-1][0], Math.max(land[i-1][2], land[i-1][3]));
			land[i][2] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][3]));
			land[i][3] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][2]));
		}

		System.out.println(Arrays.deepToString(land));
		int answer = 0;
		for(int i = 0; i < 4; i++) {
			answer = Math.max(answer, land[land.length-1][i]);
		}

		System.out.println(answer);
		return answer;
	}

	public static void main(String[] args) {

		My_Land_DP ml = new My_Land_DP();
		int[][] land = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
		ml.solution(land);
	}
}
