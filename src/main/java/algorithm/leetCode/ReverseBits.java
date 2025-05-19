package algorithm.leetCode;

public class ReverseBits {

    public int reverseBits(int n) {

        int res = 0;

        for (int  i = 0; i < 32; i++) {
            // 출력 숫자 모든 비트를 왼쪽으로 한칸씩 민다
            res <<= 1;
            // 입력 숫자의 가장 오른쪽 자리 숫자를 취해서 출력 숫자 제일 오른쪽에 반영
            res += n & 1;
            // 입력 숫자를 오른쪽으로 1칸씩 밀어서 가장 오른쪽 숫자를 버린다.
            n >>= 1;
        }

        return res;

//        return Integer.reverse(n);
    }

    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
        int x = 43261596;
        int i1 = rb.reverseBits(x);
        System.out.println(i1);
    }
}
