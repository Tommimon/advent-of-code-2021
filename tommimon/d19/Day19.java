package tommimon.d19;

import org.ode4j.math.DVector3;
import org.ode4j.math.DVector3C;
import org.ode4j.ode.DWorld;
import org.ode4j.ode.OdeHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;


public class Day19 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d19/input"));
        String txt = br.lines().filter(s -> !s.isBlank()).reduce((a,b) -> a+"\n"+b).get();
        String[] paragraph = txt.replaceAll("--- scanner [0-9]* ---\n", "---").split("---");
        DWorld world = OdeHelper.createWorld();
        Scanner[] scanners = Arrays.stream(paragraph).filter(s -> !s.isBlank()).map(s -> new Scanner(s.strip(), world)).toArray(Scanner[]::new);
        boolean[] found = new boolean[scanners.length];
        Arrays.fill(found, false);
        found[0] = true;

        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int i = 0; i < scanners.length; i++) {
                for (int j = 0; j < scanners.length; j++) {
                    if (found[i] && !found[j]) {
                        if (scanners[i].rotateAndCompare(scanners[j]))
                            found[j] = true;
                            repeat = true;
                    }
                }
            }
        }

        Vector<DVector3C> positions = new Vector<>();
        for (Scanner s: scanners) {
            for (Beacon b: s.beacons) {
                DVector3C pos = b.globalPosition();
                if (!positions.contains(pos)) {
                    positions.add(pos);
                }
            }
        }

        System.out.println(positions.size());

        int max = 0;
        for (Scanner a: scanners) {
            for (Scanner b: scanners) {
                DVector3C delta = new DVector3(b.realPos).sub(a.realPos);
                int manhattan = (int) (Math.abs(delta.get(0)) + Math.abs(delta.get(1)) + Math.abs(delta.get(2)));
                if (manhattan > max)
                    max = manhattan;
            }
        }

        System.out.println(max);
    }
}
