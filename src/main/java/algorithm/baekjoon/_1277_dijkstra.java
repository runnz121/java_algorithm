package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1277_dijkstra {

    static int N;
    static int W;
    static double M;
    static int[][] map;
    static int[] dist;
    static int FACTOR = 100_000;
    static List<Node> nodes = new ArrayList<>();
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[][] visited;
    static int factory = 1;

    static class Node {
        int x;
        int y;
        int factory;
        int weight;

        Node(int x,
             int y,
             int factory,
             int weight) {
            this.x = x;
            this.y = y;
            this.factory = factory;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());

        map = new int[N + 1][N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int x = 0; x < N; x++) {
            graph.add(new ArrayList<>());
        }
        
        // dist 초기화 필요..?
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.add(new Node(x ,y, factory, 1));
            factory++;
        }

        // 발전소 순서로 정렬 
        Collections.sort(nodes, Comparator.comparing(it -> it.factory));

        // to -> from
        visited[0][0] = true;

        for (int k = 0; k < W; k++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            graph.get(to).add(from);
            graph.get(from).add(to);

            visited[to][from] = true;
            visited[from][to] = true;
            
            // 기존에 연결되어있는것도 최단거리 초기화 필요한지 확인
            // dist[to]
            
        }

        // 마지막 발전소 -- factory == 시작지점 -> 해당 시점의 좌표 기준으로 추가 공장의 좌표가 저장되어있는 노드와 거리를 가중치로 계산 -> dijkstra 기본 알고리즘 태우는걸로 도전해보기
        for (int idx = factory - 1; idx < nodes.size(); idx++) {
            
        }
        
        

        System.out.println(graph);
    }
}
