package algorithm.programmers;

public class CollectSticker2 {

	static class Solution {
		public static int solution(int sticker[]) {
			int answer = 0;
			int len = sticker.length;

			if (len == 1) {
				return sticker[0];
			}




			return answer;
		}

		public static void main(String[] args) {

			int[] stickers = {14, 6, 5, 11, 3, 9, 2, 10};
			int res = solution(stickers);
			System.out.println(res);
		}
	}
}
