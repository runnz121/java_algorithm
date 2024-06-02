package algorithm.programmers;

import java.util.ArrayDeque;
import java.util.Deque;

public class Cache_kakao_blind {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> lruCache = new ArrayDeque<>();

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        for (int i = 0; i < cities.length; i++) {
            String ele = cities[i].toLowerCase();
            if (lruCache.size() < cacheSize && !lruCache.contains(ele)) {
                lruCache.offer(ele);
                answer += 5;
                continue;
            }
            // 캐시에 존재할 경우 -> 캐시 갱신
            if (lruCache.contains(ele)) {
                int size = lruCache.size();
                while (size > 0) {
                    String out = lruCache.poll();
                    if (!out.equals(ele)) {
                        lruCache.offer(out);
                    }
                    size --;
                }
                lruCache.offerLast(ele);

                answer += 1;
            }
            // 캐시에 존재안할 경우
            else {
                lruCache.pollFirst();
                lruCache.offerLast(ele);
                answer += 5;
            }
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {

        Cache_kakao_blind ckb = new Cache_kakao_blind();
        int cachesize = 0;
        String[] input = {"a", "b", "c", "b", "a"};
        ckb.solution(cachesize, input);
    }
}
