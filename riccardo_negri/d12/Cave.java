package riccardo_negri.d12;

import java.util.ArrayList;
import java.util.List;

public class Cave {
    private final String caveName;
    private final List<String> connectedTo = new ArrayList<>();

    public Cave (String caveName) {
        this.caveName = caveName;
    }

    public void addNearCave (String cave) {
        if (!(connectedTo.contains(cave))) {
            connectedTo.add(cave);
        }
    }

    public List<String> getNearCaves () {
        return connectedTo;
    }

    public String getCaveName () {
        return caveName;
    }
}
