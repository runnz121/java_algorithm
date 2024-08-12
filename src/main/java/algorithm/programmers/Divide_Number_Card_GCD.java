package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/135807

/**
 * 문제 아이디어
 * 철수, 영희의 배열에서 최대공약수를 구하고,
 * 철수의 최대공약수가 영희의 배열에서 나뉘는지 확인
 * 영희의 최대공약수가 철수의 배열에서 나뉘는지 확인
 *
 * 둘중 저 큰값 반환
 */
public class Divide_Number_Card_GCD {

    public int solution(int[] arrayA, int[] arrayB) {

        // 철수의 카드들로부터의 GCD
        int gcdA = gcdMultiple(arrayA);
        // 영희의 카드들로부터의 GCD
        int gcdB = gcdMultiple(arrayB);
        // 조건 1: 철수가 가진 카드들의 GCD로 영희의 카드들을 나눌 수 없는지 확인
        int resultA = cannotDivideAny(gcdA, arrayB) ? gcdA : 0;
        // 조건 2: 영희가 가진 카드들의 GCD로 철수의 카드들을 나눌 수 없는지 확인
        int resultB = cannotDivideAny(gcdB, arrayA) ? gcdB : 0;
        // 두 결과 중 더 큰 값 반환
        return Math.max(resultA, resultB);
    }

    // 배열의 모든 원소들의 GCD를 구하는 함수
    public static int gcdMultiple(int[] array) {
        int gcd = array[0];
        for (int i = 1; i < array.length; i++) {
            gcd = gcd(gcd, array[i]);
        }
        return gcd;
    }

    // 두 수의 GCD를 구하는 함수 (유클리드 호제법)
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // 배열의 어떤 원소도 x로 나눠지지 않는지 확인하는 함수
    public static boolean cannotDivideAny(int x, int[] array) {
        for (int num : array) {
            if (num % x == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Divide_Number_Card_GCD dnc = new Divide_Number_Card_GCD();
        int[] A = {10, 17};
        int[] B = {5, 20};
        dnc.solution(A, B);
    }
}
