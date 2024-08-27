package bellmanford;

import dijkstra.DijkstraPriorityQue;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {

    static int V, E, start;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {// 다음 노드의 인덱스와, 그 노드로 가는데 필요한 비용을 담고 있다.
        int idx, cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {

    }
}
