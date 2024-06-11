package algorithm.programmers;

public class Nnumber {

    public String solution(int n, int t, int m, int p) {
        StringBuilder convert = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        for(int i = 0; convert.length() <= t * m; i++){
            // Integer.toString(i, radix) -> i를 radix에 해당하는 진수로 변환함
            convert.append(Integer.toString(i, n));
        }

        for(int i = p - 1;  answer.length() < t; i += m){
            answer.append(convert.charAt(i));
        }

        return answer.toString().toUpperCase();
    }

    public static void main(String[] args) {
        Nnumber nnumber = new Nnumber();
        nnumber.solution(2, 4, 2, 1);
        nnumber.solution(16, 16, 2, 1);
        nnumber.solution(16, 16, 2, 2);
    }
}
