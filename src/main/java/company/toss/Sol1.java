package company.toss;

import java.util.Arrays;

public class Sol1 {

    public int solution(int[] levels) {
        Arrays.sort(levels);
        int index = (int) Math.ceil(levels.length * 0.75);
        if (levels.length == 0) {
            return -1;
        }
        return levels[index];
    }

    public static void main(String[] args) {
        Sol1 t = new Sol1();
        int[] x = {1,2,3,4,5,6,7,8,9};
        t.solution(x);
    }
}
