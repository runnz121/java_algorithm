package algorithm.programmers;

public class Delivery_floyd_Warshall {

    static int N, M;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        return answer;
    }

    public static void main(String[] args) {
        Delivery_floyd_Warshall t = new Delivery_floyd_Warshall();
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int k = 3;
        t.solution(N, road, k);
    }

}
