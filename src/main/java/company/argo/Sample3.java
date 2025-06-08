package company.argo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Sample3 {

    static class Post {
        String id;
        int views;
        int likes;
        long timestamp;

        public Post(String id, int views, int likes, long timestamp) {
            this.id = id;
            this.views = views;
            this.likes = likes;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Post> topPosts = new PriorityQueue<>(Comparator
                .comparingInt((Post p) -> p.views)                       // views 오름차순
                .thenComparingInt(p -> p.likes)                          // likes 오름차순
                .thenComparingLong(p -> p.timestamp));                   // 오래된 글 우선 (역순이면 내림차순 처리 필요)

        int MAX_SIZE = 3;

        List<Post> incomingPosts = List.of(
                new Post("A", 150, 10, 1000),
                new Post("B", 200, 5, 1050),
                new Post("C", 150, 15, 1010),
                new Post("D", 300, 2, 1100),
                new Post("E", 200, 7, 1030)
        );

        for (Post post : incomingPosts) {
            topPosts.offer(post);
            if (topPosts.size() > MAX_SIZE) {
                topPosts.poll(); // 우선순위 낮은 것 제거
            }
        }

        // 우선순위 높은 순으로 출력 (역순 정렬 필요)
        List<Post> result = new ArrayList<>(topPosts);
        result.sort(Comparator
                .comparingInt((Post p) -> -p.views)
                .thenComparingInt(p -> -p.likes)
                .thenComparingLong(p -> -p.timestamp));

        for (Post post : result) {
            System.out.println(post.id + " (views=" + post.views + ", likes=" + post.likes + ")");
        }
    }
}
