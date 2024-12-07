package class101;

/**
 * 1부터 자연수를 이어쓰면 1234567891011121314...가 됩니다. 이렇게 이어쓴 숫자를 A라 할 떄, n 번째 위치하는 숫자를 반환하는 함수를 만드세요
 *
 * input:
 * n = 5
 * result : 5
 *
 * input:
 * n = 15
 * result : 2
 *
 */
public class Sol1 {

    public int solution(int n) {
        int length = 1; //현재 탐색 자릿수 (예: 한 자리, 두 자리 숫자 등)
        long count = 9; // 현재 자릿수 숫자 갯수 (한 자릿수는 9개: 1~9)
        int base = 1; // 현재 자릿수 시작숫자 (예: 한 자릿수는 1, 두 자릿수는 10)

        // n이 속한 자릿수를 찾기
        while (n > length * count) {
            n -= length * count;
            length += 1;
            base *= 10;
            count *= 10;
        }

        // n번째 숫자가 속한 자연수를 계산
        int number = (n - 1) / length + base;
        System.out.println(number);
        // 해당 자연수를 문자열로 치환 (이 때 몇 자리 수인지 확인 후 나눠줌)
        int i = String.valueOf(number).charAt((n - 1) % length) - '0';
        System.out.println(i);
        return i;
    }


    public static void main(String[] args) {

        Sol1 s1 = new Sol1();
//        s1.solution(5);
        s1.solution(25);

    }
}
