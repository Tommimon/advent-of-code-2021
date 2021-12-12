package mynam3isg00d.d12.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public Graph parseInput(String input) {
        File file;
        Scanner scanner = null;
        String inp = new String();
        Graph g = new Graph();

        try {
            file = new File(input);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextLine()) {
            inp = inp + scanner.nextLine() + "\n";
        }

        String[] lines = inp.split("\n");
        for(String l : lines) {
            String first = l.split("-")[0];
            String last = l.split("-")[1];
            //add first, if missing
            if(g.inGraph(first) == null) {
                Node n = new Node(first);
                g.addNode(n);
                if(!first.toLowerCase().equals(first)) g.bigCaves.add(n);
            }
            //add second, if missing
            if(g.inGraph(last) == null) {
                Node n = new Node(last);
                g.addNode(n);
                if(!last.toLowerCase().equals(last)) g.bigCaves.add(n);
            }
            //add relation
            g.inGraph(first).addNeighbor(g.inGraph(last));
            g.inGraph(last).addNeighbor(g.inGraph(first));
        }
        return g;
    }
}
