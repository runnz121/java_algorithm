package algorithm.leetCode;

import java.util.Arrays;

public class AssignCookies {

	public int findContentChildren(int[] g, int[] s) {

		Arrays.sort(g);
		Arrays.sort(s);

		int i = 0; // 어린이 인덱스
		int j = 0; // 쿠키 인덱스

		// 어린이 인덱스와 쿠키 인덱스가 각각 전체 길이보다 작을 때 반복
		while (i < g.length && j < s.length) {
			// 어린이가 원하는 크기보다 쿠키가 더 클 때만 조건이 만족되고 다음 어린이로 이동
			if (s[j] >= g[i]) {
				i ++;
			}
			j ++; // 다음 쿠키로 이동
		}
		return i;
	}

	public static void main(String[] args) {
		AssignCookies asc = new AssignCookies();
		int[] g = {1,2,3};
		int[] s = {1,1};
		int res = asc.findContentChildren(g, s);
		System.out.println(res);
	}
}
