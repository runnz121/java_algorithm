package algorithm.programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/43163
public class Word_change_bfs_dfs {

    static boolean[] visited;
    static int answer = 0;

    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        dfs(begin, target, words, 0);
        return answer;
    }

    public static void dfs(String begin, String target, String[] words, int cnt) {
        // 같은 글자면 종료
        if (begin.equals(target)) {
            answer = cnt;
            return;
        }

        // 이미 방문한 글자인 경우 패스
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 들어온 글자와 배열에 존재하고있는 방문하지 않은 글자 중 같은 글자 세기
            int k = 0;
            for (int j = 0; j < begin.length(); j++) {
                if (begin.charAt(j) == words[i].charAt(j)) {
                    k++;
                }
            }
            // 한글자 빼고 모두 같은 경우
            if (k == begin.length() - 1) {
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Word_change_bfs_dfs bcdf = new Word_change_bfs_dfs();
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        bcdf.solution(begin, target, words);
    }
}
