package algorithm.programmers;

import java.util.ArrayList;

public class Delivery_dijkstra {

    // v: 정점 갯수, E : 간선 갯수
    static int V, E, start;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        V = N;
        E = road.length;




        return answer;
    }

    public static void main(String[] args) {
        Delivery_dijkstra t = new Delivery_dijkstra();
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int k = 3;
        t.solution(N, road, k);
    }

}
