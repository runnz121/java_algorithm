package algorithm.programmers;

import java.util.*;

public class Develop_function_stack_que {

    static List<Integer> tasks = new ArrayList<>();

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Deque<Icon> que = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            que.offer(new Icon(progresses[i], speeds[i]));
        }

        while (!que.isEmpty()) {

            Integer firstProgress = que.peek().progress;

            if (firstProgress < 100) {
                for (int x = 0; x < que.size(); x++) {
                    Icon out = que.poll();
                    if (out.progress < 100) {
                        out.progress += out.speeds;
                    }
                    que.offer(out);
                }
            } else {
                int task = 0;
                while (true) {
                    if (que.isEmpty()) {
                        break;
                    }
                    Icon completeTask = que.peek();
                    if (completeTask.progress >= 100) {
                        que.poll();
                        task ++;
                    }
                    if (completeTask.progress < 100) {
                        break;
                    }
                }
                tasks.add(task);
            }
        }
        answer = tasks.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    static class Icon {
        Integer progress;
        Integer speeds;

        Icon(Integer progress, Integer speeds) {
            this.progress = progress;
            this.speeds = speeds;
        }
    }

    public static void main(String[] args) {
        Develop_function_stack_que ds = new Develop_function_stack_que();
        int[] progress = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        ds.solution(progress, speeds);
    }
}
