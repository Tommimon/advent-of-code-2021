package john_galt_10.d12;

import java.util.ArrayList;
import java.util.List;

public class Cave {
    private String name;
    private boolean isSmall;
    private boolean isVisited;
    private List<Cave> adiacenti;

    public Cave (String name) {
        this.name = name;
        if ('A' <= name.charAt(0) && 'Z' >= name.charAt(0)) isSmall = true;
        isVisited = false;
        adiacenti = new ArrayList<>();
    }

    public void addAdiacente(Cave c) {
        if (!adiacenti.contains(c)) adiacenti.add(c);
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Cave)) return false;
        Cave c = (Cave) o;
        return (c.getName().equals(name));
    }

    @Override
    public String toString() {
        String sAdiacenti = "";
        for (Cave c : adiacenti) sAdiacenti += c.getName();
        return "Cave{" +
                "name='" + name + '\'' +
                ", isSmall=" + isSmall +
                ", isVisited=" + isVisited +
                ", adiacenti=" + sAdiacenti +
                '}';
    }
}
