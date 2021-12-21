package tommimon.d21;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;


public class Part2 {
    static HashMap<String, Long[]> hashMap = new HashMap<>();

    static Long[] countWins(Long[] posScoresTurn) {
        if (posScoresTurn[2] >= 21)
            return new Long[]{1L, 0L};
        if (posScoresTurn[3] >= 21)
            return new Long[]{0L, 1L};
        if (hashMap.containsKey(str(posScoresTurn)))
            return hashMap.get(str(posScoresTurn));
        else {
            Long[] res = new Long[]{0L, 0L};
            for (int i = 1; i <= 3; i++)
                for (int j = 1; j <= 3; j++)
                    for (int k = 1; k <= 3; k++) {
                        Long[] partial;
                        if (posScoresTurn[4] == 1L)
                            partial = countWins(new Long[]{
                                    (posScoresTurn[0]+i+j+k) % 10,
                                    posScoresTurn[1],
                                    posScoresTurn[2] + (posScoresTurn[0]+i+j+k)%10 + 1,
                                    posScoresTurn[3],
                                    2L
                            });
                        else
                            partial = countWins(new Long[]{
                                    posScoresTurn[0],
                                    (posScoresTurn[1]+i+j+k) % 10,
                                    posScoresTurn[2],
                                    posScoresTurn[3] + (posScoresTurn[1]+i+j+k)%10 + 1,
                                    1L
                            });
                        Arrays.setAll(res, index -> res[index] + partial[index]);
                    }
            hashMap.put(str(posScoresTurn), res);
            return res;
        }
    }

    static String str(Long[] a) {
        StringBuilder res = new StringBuilder("[");
        for (Long i: a) {
            res.append(i.toString());
            res.append(",");
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d21/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        long pos1 = Integer.parseInt(lines.get(0).split("Player 1 starting position: ")[1]) - 1;
        long pos2 = Integer.parseInt(lines.get(1).split("Player 2 starting position: ")[1]) - 1;
        System.out.println(Arrays.stream(countWins((new Long[]{pos1, pos2, 0L, 0L, 1L}))).max(Long::compareTo).get());
    }
}
