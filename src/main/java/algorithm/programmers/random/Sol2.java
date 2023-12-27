package algorithm.programmers.random;

public class Sol2 {

    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        return answer;
    }


    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
        int[] bandage = {1, 1, 1};
        int health = 5;
        int[][] attacks = {{1,2}, {3,2}};
        int res = s1.solution(bandage, health, attacks);
        System.out.println(res);
    }
}
