package company.zum;

public class Sol1 {
    public long solution(long n) {
        int maximumValue = 100_000;
        long answer = 0;

        for (int i = 1; i < maximumValue; i++ ) {
            int share = (int)(i / n);
            int remain = (int)(i % n);
            // 나머지가 n보다 크거나 같으면 종료
            if (remain >= n) {
                break;
            }
            // 몫과 나머지가 같으면 더해준다
            if (share == remain) {
                answer += i;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Sol1 t = new Sol1();
        int n = 3;
        System.out.println(t.solution(n));
    }
}
