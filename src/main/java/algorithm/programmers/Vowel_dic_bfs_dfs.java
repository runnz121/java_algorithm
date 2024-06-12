package algorithm.programmers;

import java.util.ArrayList;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/84512
public class Vowel_dic_bfs_dfs {

    static boolean [] visited;
    static String[] vowels = new String[]{"A", "E", "I", "O", "U"};
    static int count;
    static int answer;

    public int solution(String word) {

        visited = new boolean[5];

        backtracking(0, "", word);

        System.out.println(answer);
        return answer;
    }

    private void backtracking(int startIdx, String str, String targetWord) {

        if (str.equals(targetWord)) {
            answer = count;
            return;
        }

        if (startIdx == 5) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            StringBuilder stb = new StringBuilder();
            stb.append(str);
            stb.append(vowels[i]);

            count += 1;
            visited[i] = true;
            backtracking(startIdx + 1, stb.toString(), targetWord);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Vowel_dic_bfs_dfs vfs = new Vowel_dic_bfs_dfs();
        String words = "AAAE";
        vfs.solution(words);
    }
}
