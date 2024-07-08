package algorithm.programmers;


public class Under2Difference_BitMask {

    public long[] solution(long[] nums) {
        long[] ans = new long[nums.length];
        int idx = 0;
        for (long num : nums) {
            // 1 left shift
            for (long i = 1; ; i <<= 1) {
                if ((num & i) == 0) { // AND 연산
                    num |= i;   // OR 연산
                    num ^= (i >> 1); // XOR 연산
                    break;
                }
            }
            ans[idx++] = num;
        }
        return ans;
    }

    public static void main(String[] args) {
        Under2Difference_BitMask u2d = new Under2Difference_BitMask();
        long[] inputs = {2L, 7L};
        u2d.solution(inputs);
    }
}
