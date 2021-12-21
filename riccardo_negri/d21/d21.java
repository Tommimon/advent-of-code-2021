//
// Day 21 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 713328
// Second part solution: 92399285032143
//

package riccardo_negri.d21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d21 {
    public static HashMap<String, Long[]> history = new HashMap<>();

    public static void main (String[] args) throws Exception {
        File f = new File("riccardo_negri/d21/input.txt");
        Scanner reader = null;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        String line = reader.nextLine();
        int pos1 = Integer.parseInt(line.split(": ")[1]) - 1;
        line = reader.nextLine();
        int pos2 = Integer.parseInt(line.split(": ")[1]) - 1;
        int pos1start = pos1;
        int pos2start = pos2;

        // part 1
        int dice = 1;
        int score1 = 0, score2 = 0;
        int playing = 1;
        int rolled = 0;
        while (score1 < 1000 && score2 < 1000) {
            int move = 0;
            for (int i = 0; i < 3; i++) {
                if (dice > 100) {
                    dice = 1;
                }
                move += dice;
                dice++;
                rolled++;
            }
            if (playing == 1) {
                pos1 += move;
                pos1 = pos1 % 10;
                score1 += pos1 + 1;
                playing = 2;
            }
            else {
                pos2 += move;
                pos2 = pos2 % 10;
                score2 += pos2 + 1;
                playing = 1;
            }
        }

        int minScore;
        if (score1 > 1000) {
            minScore = score2;
        }
        else {
            minScore = score1;
        }
        System.out.println(rolled * minScore);

        // part 2
        Long[] wins = playAllUniverses(pos1start, 0, pos2start, 0, 1);
        if (wins[0] > wins[1]) {
            System.out.println(wins[0]);
        }
        else {
            System.out.println(wins[1]);
        }
    }

    public static Long[] playAllUniverses (int pos1, int score1, int pos2, int score2, int playing) {
        // use history
        if (history.containsKey(pos1 + "-" + score1 + "-" + pos2 + "-" + score2 + "-" + playing)) {
            return history.get(pos1 + "-" + score1 + "-" + pos2 + "-" + score2 + "-" + playing);
        }
        // base case
        if (score1 >= 21) {
            return new Long[]{1L, 0L};
        }
        if (score2 >= 21) {
            return new Long[]{0L, 1L};
        }

        // quantum die rolls
        Long[] results = new Long[]{0L, 0L};
        for (int first = 1; first <= 3; first++) {
            for (int second = 1; second <= 3; second++) {
                for (int third = 1; third <= 3; third++) {
                    if (playing == 1) {
                        int tpos1 = pos1 + first + second + third;
                        tpos1 = tpos1 % 10;
                        int tscore1 = tpos1 + 1 + score1;
                        int tplaying = 2;
                        Long[] temp = playAllUniverses(tpos1, tscore1, pos2, score2, tplaying);
                        history.put(tpos1 + "-" + tscore1 + "-" + pos2 + "-" + score2 + "-" + tplaying, temp);
                        results[0] += temp[0];
                        results[1] += temp[1];
                    }
                    else {
                        int tpos2 = pos2 + first + second + third;
                        tpos2 = tpos2 % 10;
                        int tscore2 = tpos2 + 1 + score2;
                        int tplaying = 1;
                        Long[] temp = playAllUniverses(pos1, score1, tpos2, tscore2, tplaying);
                        history.put(pos1 + "-" + score1 + "-" + tpos2 + "-" + tscore2 + "-" + tplaying, temp);
                        results[0] += temp[0];
                        results[1] += temp[1];
                    }
                }
            }
        }
        return results;
    }
}