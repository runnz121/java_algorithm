package algorithm.programmers;

import java.util.HashSet;
import java.util.Set;

// 시간초과가 난다면 boolean 배열로 바꿔보자 ..
// https://school.programmers.co.kr/learn/courses/30/lessons/17679
public class Friends_4_blocks {

    public int solution(int m, int n, String[] board) {
        int answer = 0;

        String[][] newBoard = new String[m][n];

        for (int i = 0; i < m; i++) {
            String[] split = board[i].split("");
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = split[j];
            }
        }


        while (true) {

            // 중복 불럭 모두 저장
            Set<String> deletedBlocks = new HashSet<>();
            boolean flag = true;
            boolean[][] check = new boolean[m][n];

            // 2 * 2 블럭 찾음
            for (int x = 0; x < m - 1; x++) {
                for (int y = 0; y < n - 1; y++) {
                    if (newBoard[x][y].equals("-")) {
                        continue;
                    }

                    String upFirst = newBoard[x][y];
                    String upSecond = newBoard[x][y + 1];
                    String downFirst = newBoard[x + 1][y];
                    String downSecond = newBoard[x + 1][y + 1];

                    // 모두 같으면 좌표 더함
                    if (!upFirst.equals("-") && upFirst.equals(upSecond) && upSecond.equals(downFirst) && downFirst.equals(downSecond)) {
                        check[x][y] = true;
                        check[x][y + 1] = true;
                        check[x + 1][y] = true;
                        check[x + 1][y + 1] = true;
                        flag = false;
                    }
                }
            }

            // 더이상 뺄 블럭이 없으면 종료
            if (flag) {
                break;
            }

            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(check[i][j]){
                        newBoard[i][j] = "-";
                        answer++;
                    }
                }
            }

            // 블럭 내리기 (아래서부터 시작)
            for (int y1 = m - 1; y1 >= 0; y1--) {
                for (int x1 = 0; x1 < n; x1++) {

                    if (newBoard[y1][x1].equals("-")) {
                        // "-" 가 아닌 것을 찾은 후 "-"와 바꿔준다
                        for (int k = y1; k >= 0; k--) {
                            if (newBoard[k][x1].equals("-")) {
                                continue;
                            }
                            newBoard[y1][x1] = newBoard[k][x1];
                            newBoard[k][x1] = "-";
                            break;
                        }
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Friends_4_blocks f4b = new Friends_4_blocks();
        String [] inputs1 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"}; // 6, 6
        String[] inputs = {"CCBDE", "AAADE", "AAABF", "CCBBF"}; // 4, 5
//        f4b.solution(4, 5, inputs);
        f4b.solution(6, 6, inputs1);
    }
}
