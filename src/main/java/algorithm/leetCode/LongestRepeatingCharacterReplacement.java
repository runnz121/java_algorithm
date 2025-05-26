package algorithm.leetCode;

/**
 * 1. 알파벳 고정 길이를 둔다.
 * 2. left, right 인덱스를 둔다
 * 3. 슬라이딩 윈도우 (right 를 증가) 통해 k 만큼 숫자를 바꿀 수 있는지 확인
 * https://www.algodale.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s,
                                    int k) {

        int maxFreq = 0;
        int answer = 0;
        // 알파벳 빈도 저장
        int[] count = new int[26];
        char[] charArray = s.toCharArray();

        int left = 0;
        for (int right = 0; right < charArray.length; right++) {

            char current = charArray[right];
            count[current - 'A'] ++;

            //윈도우 안에서 가장 많이 등장한 문자(최빈도)의 수를 최신화
            maxFreq = Math.max(maxFreq, count[current - 'A']);

            // 윈도우 - 최빈도값 이 k 보다 큰 경우 윈도우 축소
            while ((right - left + 1) - maxFreq > k) {
                count[charArray[left] - 'A']--;
                left++;
            }

            // 현재 윈도우 길이로 최대값 갱신
            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement lr = new LongestRepeatingCharacterReplacement();
        String s = "ABAB";
        int k = 2;
        int i1 = lr.characterReplacement(s, k);
        System.out.println(i1);
    }
}
