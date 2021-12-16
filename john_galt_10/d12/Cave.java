package john_galt_10.d12;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.List;

public class Cave {
    private String name;
    private boolean isSmall;
    private boolean visited;
    private List<Cave> adiacenti;

    public Cave (String name) {
        this.name = name;
        if (!('A' <= name.charAt(0) && 'Z' >= name.charAt(0))) isSmall = true;
        visited = false;
        adiacenti = new ArrayList<>();
    }

    public void addAdiacente(Cave c) {
        if (!adiacenti.contains(c)) adiacenti.add(c);
    }

    public String getName() {
        return name;
    }

    public void setVisited(boolean visited) { this.visited = visited; }

    public boolean isVisited() { return visited; }

    public boolean isSmall() { return isSmall; }

    public List<Cave> getAdiacenti() {
        return adiacenti;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Cave)) return false;
        Cave c = (Cave) o;
        return (c.getName().equals(name));
    }

    @Override
    public String toString() {
        String sAdiacenti = "";
        for (Cave c : adiacenti) sAdiacenti += (" " + c.getName());
        return "Cave{" +
                "name='" + name + '\'' +
                ", isSmall=" + isSmall +
                ", isVisited=" + visited +
                ", adiacenti=" + sAdiacenti +
                '}';
    }
}
