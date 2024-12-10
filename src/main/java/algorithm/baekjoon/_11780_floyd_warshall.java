package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _11780_floyd_warshall {

    static int N;
    static int M;
    static int[][] dist;
    static int[][] path;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        path = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    path[i][j] = Integer.MAX_VALUE;
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                    path[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken()) - 1;
            int from = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            dist[to][from] = Math.min(w, dist[to][from]);
            path[to][from] = to;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // Integer over flow로 인한 If 조건 추가 -> -2147483648
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        if ( dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            // 도착전 들른 곳 저장
                            path[i][j] = path[k][j];
                        }
                    }
                }
            }
        }

//        System.out.println(Arrays.deepToString(path));

        for(int i = 0 ; i < N ; i++){
            for(int j = 0; j < N ; j++){
                sb.append(dist[i][j] == Integer.MAX_VALUE ? 0 + " " : dist[i][j] + " ");
            }
            sb.append("\n");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 자기 자신은 같음으로 0 출력
                if (path[i][j] == Integer.MAX_VALUE) {
                    sb.append("0\n");
                } else {
                    // i -> j 이동일 경우 j 계속 타고 들어가서 i 만날때까지 스택 넣음 == 마지막 도착부터 시작까지 거꾸로
                    int end = j;
                    // 최종 도착 지점 넣음 (역순으로 들어감)
                    stack.push(end + 1);
                    // 시작점 만나기 전까지 들른곳 모두 넣음
                    while (i != path[i][end]) {
                        end = path[i][end];
                        stack.push(end + 1);
                    }
                    // 시작점 넣음
                    stack.push(i + 1);
                    sb.append(stack.size()); // 도시의 갯수 k (최소 비용에 포함되어있는 == 들른 도시의 갯수)
                    while (stack.isEmpty() == false) {
                        sb.append(" " + stack.pop());
                    }
                    sb.append("\n");
                }
            }
        }

//        System.out.println(Arrays.deepToString(dist));
//        System.out.println(Arrays.deepToString(path));
        System.out.println(sb.toString());
    }
}
