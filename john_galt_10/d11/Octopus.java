package john_galt_10.d11;

import java.util.ArrayList;
import java.util.List;

public class Octopus {
    private int energyLevel;
    private List<Octopus> neighbors;
    boolean flashed;

    public Octopus(int starting) {
        energyLevel = starting;
        neighbors = new ArrayList<>();
        flashed = false;
    }

    public void attachOctopus(Octopus o) {
        neighbors.add(o);
    }

    public boolean isFlashed() {
        return flashed;
    }

    public void resetEnergyLevel() {
        energyLevel = 0;
        flashed=false;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }
    public int energyIncrease() {
        int flashedCount = 0;
        if (energyLevel < 9 && !flashed) {
            energyLevel++;
        } else if (!flashed){
            flashed = true;
            flashedCount += flash();
        }
        //System.out.println("aumentato!");

        return flashedCount;
    }

    private int flash() {
        int flashed = 1;
        for (Octopus o : neighbors) {
            flashed += o.energyIncrease();
        }
        return flashed;
    }
}
