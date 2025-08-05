package algorithm.hackerank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.hackerrank.com/challenges/java-comparator/problem
public class Java_comparator {

    public static class PlayerInfo {
        String name;
        Integer score;

        public PlayerInfo(String name, Integer score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        List<PlayerInfo> players = new ArrayList<>();

        for (int i = 0; i < T; i++) {

            String[] info = br.readLine().trim().split("\\s+");

            players.add(new PlayerInfo(info[0], Integer.parseInt(info[1])));
        }

//        players.sort(new Comparator<PlayerInfo>() {
//            @Override
//            public int compare(PlayerInfo a, PlayerInfo b) {
//
//                int score = b.score.compareTo(a.score);
//
//                if (score != 0) {
//                    return score;
//                }
//                return a.name.compareTo(b.name);
//            }
//        });

        players.sort((a, b) -> {

            int score = b.score.compareTo(a.score);

            if (score != 0) {
                return score;
            }
            return a.name.compareTo(b.name);
        });

        for (int i = 0; i < players.size(); i++) {
            PlayerInfo info = players.get(i);

            System.out.println(info.name + " " + info.score);
        }
    }
}
