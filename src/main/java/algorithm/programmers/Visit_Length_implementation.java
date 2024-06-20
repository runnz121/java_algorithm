package algorithm.programmers;

import java.util.HashSet;

public class Visit_Length_implementation {

    public int solution(String dirs) {
        int answer = 0;
        HashSet<String> dis = new HashSet<>();
        int curX = 0;
        int curY = 0;


        for (int i = 0; i < dirs.length(); i++) {
            int nextX = curX;
            int nextY = curY;
            String pos = "";

            char c = dirs.charAt(i);
            if (c == 'U') {
                nextY += 1;
                pos += curX + "" + curY + "" + nextX+ "" + nextY;
            } else if (c == 'D') {
                nextY -= 1;
                pos += nextX + "" + nextY + "" + curX+ "" + curY;
            } else if (c == 'R') {
                nextX += 1;
                pos += curX + "" + curY + "" + nextX+ "" + nextY;
            } else if (c == 'L') {
                nextX -= 1;
                pos += nextX + "" + nextY + "" + curX+ "" + curY;
            }

            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue;
            }

            dis.add(pos);

            curX = nextX;
            curY = nextY;
        }

        answer = dis.size();
        return answer;
    }

    public static void main(String[] args) {
        Visit_Length_implementation vli = new Visit_Length_implementation();
        String inputs = "ULURRDLLU";
        vli.solution(inputs);
    }
}
