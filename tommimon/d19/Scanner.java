package tommimon.d19;

import java.util.Arrays;

import org.ode4j.math.DMatrix3;
import org.ode4j.math.DMatrix3C;
import org.ode4j.math.DVector3;
import org.ode4j.math.DVector3C;
import org.ode4j.ode.DBody;
import org.ode4j.ode.DWorld;
import org.ode4j.ode.OdeHelper;
import org.ode4j.ode.internal.Rotation;

import static org.ode4j.ode.internal.Common.M_PI;

public class Scanner{
    Beacon[] beacons;
    DBody body;
    DMatrix3C realRot;
    DVector3C realPos;
    static DVector3C[] axis = {
            new DVector3(0, 0, 1),
            new DVector3(0, 0, 1),
            new DVector3(0, 0, 1),
            new DVector3(0, 0, 1),
            new DVector3(0, 1, 0),
            new DVector3(0, 1, 0),
    };
    static double[] angles = {
            0,
            M_PI/2,
            M_PI,
            -M_PI/2,
            M_PI/2,
            -M_PI/2
    };

    public Scanner(String str, DWorld world) {
        beacons = Arrays.stream(str.split("\n")).map(s-> new Beacon(s, this)).toArray(Beacon[]::new);
        body = OdeHelper.createBody(world);
        realRot = body.getRotation();
        realPos = body.getPosition();
    }

    DMatrix3 floor(DMatrix3 m) {
        m.set00(Math.round(m.get00()));
        m.set01(Math.round(m.get01()));
        m.set02(Math.round(m.get02()));
        m.set10(Math.round(m.get10()));
        m.set11(Math.round(m.get11()));
        m.set12(Math.round(m.get12()));
        m.set20(Math.round(m.get20()));
        m.set21(Math.round(m.get21()));
        m.set22(Math.round(m.get22()));
        return m;
    }


    void rotate(DMatrix3 rotation) {
        body.setRotation(floor(rotation));
    }

    boolean compare(Scanner other) {
        for (Beacon baseA: beacons) {
            for (Beacon baseB: other.beacons) {
                DVector3 delta = baseA.globalPosition().sub(baseB.globalPosition());
                int counter = 0;
                for (Beacon a: beacons) {
                    for (Beacon b: other.beacons) {
                        DVector3 v = b.globalPosition().add(delta);
                        if (v.isEq(a.globalPosition()))
                            counter++;
                    }
                }
                if(counter >= 12) {
                    other.realRot = other.body.getRotation().clone();
                    other.realPos = delta;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean rotateAndCompare(Scanner other) {
        for (int i = 0; i < axis.length; i++) {
            DMatrix3 baseRot = new DMatrix3();
            Rotation.dRFromAxisAndAngle(baseRot, axis[i], angles[i]);
            for (int j = 0; j < 4; j++) {
                DMatrix3 additionalRot = new DMatrix3();
                Rotation.dRFromAxisAndAngle(additionalRot, new DVector3(1, 0, 0), M_PI*j/2);
                DMatrix3 totalRot = new DMatrix3();
                totalRot.set00(additionalRot.dotRow(0, baseRot.columnAsNewVector(0)));
                totalRot.set01(additionalRot.dotRow(0, baseRot.columnAsNewVector(1)));
                totalRot.set02(additionalRot.dotRow(0, baseRot.columnAsNewVector(2)));
                totalRot.set10(additionalRot.dotRow(1, baseRot.columnAsNewVector(0)));
                totalRot.set11(additionalRot.dotRow(1, baseRot.columnAsNewVector(1)));
                totalRot.set12(additionalRot.dotRow(1, baseRot.columnAsNewVector(2)));
                totalRot.set20(additionalRot.dotRow(2, baseRot.columnAsNewVector(0)));
                totalRot.set21(additionalRot.dotRow(2, baseRot.columnAsNewVector(1)));
                totalRot.set22(additionalRot.dotRow(2, baseRot.columnAsNewVector(2)));
                other.rotate(totalRot);
                if (compare(other)) {
                    other.rotate(new DMatrix3(realRot));
                    return true;
                }
            }
        }
        other.rotate(new DMatrix3(realRot));
        return false;
    }
}
