package tommimon.d19;

import org.ode4j.math.DMatrix3C;
import org.ode4j.math.DVector3;

import java.util.Arrays;

public class Beacon {
    DVector3 relativePos;
    Scanner parent;

    public Beacon(String str, Scanner parent) {
        Integer[] coords = Arrays.stream(str.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        relativePos = new DVector3(coords[0], coords[1], coords[2]);
        this.parent = parent;
    }

    public DVector3 globalPosition() {
        DMatrix3C m = parent.realRot;
        return new DVector3(m.dotRow(0, relativePos), m.dotRow(1, relativePos), m.dotRow(2, relativePos)).add(parent.realPos);
    }
}
