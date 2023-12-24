package coupang;

import java.util.HashMap;
import java.util.Map;

public class Sol1 {

	public int solution(String number) {
		int answer = 0;
		Map<Character, String> brokenKeyboard = new HashMap<>() {{
			put('0', "0");
			put('1', "12");
			put('2', "23");
			put('3', "34");
			put('4', "45");
			put('5', "56");
			put('6', "67");
			put('7', "78");
			put('8', "89");
			put('9', "90");
		}};

		int size = number.length();
		char[] toCharNumber = number.toCharArray();

		StringBuilder compareResult = new StringBuilder();
		int idx = 0;

		while (idx < size) {

			char key = toCharNumber[idx];
			boolean checkContinue = false;

			String value = brokenKeyboard.get(key);

			if (idx + 1 != size) {
				char nextKey = toCharNumber[idx + 1];
				checkContinue = continueChar(String.valueOf(value), nextKey);
			}

			if (!checkContinue) {
				value = value.substring(0,1);
				compareResult.append(value);
				answer += 2;
			} else {
				compareResult.append(value);
				idx += 1;
				answer += 1;
			}
			idx += 1;
		}

		if (compareResult.length() != number.length()) {
			if (toCharNumber[size -1] != '0') {
				answer += 2;
			}
			answer += 1;
		}

		return answer;
	}

	//
	private boolean continueChar(String value, char nextKey) {

		char[] valueChar = value.toCharArray();
		if (valueChar[valueChar.length - 1] == nextKey) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Sol1 s1 = new Sol1();
		String numbers2 = "100";
		int res2 = s1.solution(numbers2);
		System.out.println(res2);
	}
}
