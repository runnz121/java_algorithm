package algorithm.programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/43163
public class Word_change_bfs_dfs {

    public int solution(String begin, String target, String[] words) {
        int answer = 0;



        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Word_change_bfs_dfs bcdf = new Word_change_bfs_dfs();
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        bcdf.solution(begin, target, words);
    }
}
