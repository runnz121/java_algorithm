package company.navercloud;

//문자열 S가 주어졌을 때, S의 proper prefix(자기 자신을 제외한 접두사)이면서 proper suffix(자기 자신을 제외한 접미사)이기도 한 문자열 중 가장 긴 문자열의 길이를 반환하라.
//
//예를 들어,
//
//S = "abbabba"인 경우, 함수는 4를 반환해야 한다.
//
//"abba"는 S의 proper prefix이자 proper suffix이다.
//
//S = "codility"인 경우, 함수는 0을 반환해야 한다.
//
//proper prefix이자 suffix인 문자열이 존재하지 않기 때문이다.
//
//조건
//
//S의 길이는 1 이상 1,000,000 이하
//
//S는 오직 소문자 알파벳(a-z)으로만 구성됨

public class Sol1 {

    public int solution(String S) {

        int n = S.length();
        if (n <= 1) return 0;  // proper prefix/suffix 가 존재하려면 길이가 2 이상이어야 함

        int[] pi = new int[n];
        // π[0] = 0 으로 초기화되어 있음
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            // S[i] 에 맞출 수 있는 가장 긴 border(j)를 찾을 때까지 뒤로 당김
            while (j > 0 && S.charAt(i) != S.charAt(j)) {
                j = pi[j - 1];
            }
            // 같으면 border 길이 하나 확장
            if (S.charAt(i) == S.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        // π[n-1]은 proper prefix & suffix 중 최장 길이
        return pi[n - 1];
    }

    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
        int solution = s1.solution("abcabc");
        System.out.println(solution);
    }
}
