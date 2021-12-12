package mynam3isg00d.d12.src;

import java.util.ArrayList;

public class Node {
    String name;
    ArrayList<Node> neighbors;

    Node(String n) {
        name = n;
        neighbors = new ArrayList<Node>();
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }
}
