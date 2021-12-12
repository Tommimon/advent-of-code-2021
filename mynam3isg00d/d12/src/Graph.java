package mynam3isg00d.d12.src;

import java.util.ArrayList;

public class Graph {
    ArrayList<Node> bigCaves;
    ArrayList<Node> nodes;
    int numOfPaths;

    Graph() {
        numOfPaths = 0;
        nodes = new ArrayList<Node>();
        bigCaves = new ArrayList<Node>();
    }

    public Node inGraph(String s) {
        for(Node n : nodes) {
            if (n.name.equals(s)) return n;
        }
        return null;
    }

    public void addNode(Node n) {
        nodes.add(n);
    }

    public void print() {
        for(Node n : nodes) {
            if(bigCaves.contains(n)) System.out.print("BIG ");
            System.out.print("Node " + n.name + ": ");
            for(Node k : n.neighbors) {
                System.out.print(k.name + ", ");
            }
            System.out.print("\n");
        }
    }

    public void visitAll(Node s, ArrayList<Node> smallVisited, ArrayList<Node> visited) {
        if(s.name.equals("end")) {
            numOfPaths++;
            System.out.print("PATH: ");
            for(Node n : visited) {
                System.out.print(n.name + ", ");
            }
            System.out.print("end\n");
        }

        if (visited == null) {
            visited = new ArrayList<Node>();
        }
        ArrayList<Node> visitedCopy = new ArrayList<Node>(visited);
        visitedCopy.add(s);


        if (smallVisited == null) {
            smallVisited = new ArrayList<Node>();
        }
        ArrayList<Node> smallVisitedCopy = new ArrayList<>(smallVisited);
        if (!(bigCaves.contains(s))) {
            //Cave is small, add it to smallVisited
            smallVisitedCopy.add(s);
        }

        boolean isStuck = true;
        for(Node n : s.neighbors) {
            if(!(smallVisited.contains(n))) {
                isStuck = false;
                visitAll(n, smallVisitedCopy, visitedCopy);
            }
        }

        if (isStuck) {
            //System.out.print("ERROR: node " + s.name + " has no available neighbors; returning\n");
            return;
        }
    }

    public void visitAll2(Node s, Node ex, ArrayList<Node> smallVisited, ArrayList<Node> visited, boolean exV) {
        if(s.name.equals("end")) {
            numOfPaths++;
            System.out.print("PATH: ");
            for(Node n : visited) {
                System.out.print(n.name + ", ");
            }
            System.out.print("end\n");
            return;
        }

        if (visited == null) {
            visited = new ArrayList<Node>();
        }
        ArrayList<Node> visitedCopy = new ArrayList<Node>(visited);
        visitedCopy.add(s);


        if (smallVisited == null) {
            smallVisited = new ArrayList<Node>();
        }
        ArrayList<Node> smallVisitedCopy = new ArrayList<>(smallVisited);
        if (!(bigCaves.contains(s))) {
            if (s.equals(ex) && exV) {
                exV = false;
            } else {
                smallVisitedCopy.add(s);
            }
        }

        boolean isStuck = true;
        for(Node n : s.neighbors) {
            if(!(smallVisited.contains(n))) {
                isStuck = false;
                visitAll2(n, ex, smallVisitedCopy, visitedCopy, exV);
            }
        }

        if (isStuck) {
            //System.out.print("ERROR: node " + s.name + " has no available neighbors; returning\n");
            return;
        }
    }
}
