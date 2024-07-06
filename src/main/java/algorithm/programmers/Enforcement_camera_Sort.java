package algorithm.programmers;

import java.util.Arrays;
import java.util.Comparator;

public class Enforcement_camera_Sort {

    static int MIN_OUT = -30001;

    public int solution(int[][] routes) {
        int answer = 0;

        // 카메라 설치가 가능한 곳은 진출 시점 기준으로 판단
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];

            int in = route[0];
            int out = route[1];

            // 진입 값보다 자은 경우만 갱신
            if (in > MIN_OUT) {
                MIN_OUT = out;
                answer ++;
            }
        }
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Enforcement_camera_Sort ecs = new Enforcement_camera_Sort();
        int[][] inputs = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        ecs.solution(inputs);
    }
}
