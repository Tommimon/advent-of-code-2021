package Gonduls.d12;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Input {

    private final HashMap<String, List<String>> connections = new HashMap<>();

    public Input(String filePath) throws IOException {
        Scanner scan = new Scanner(new File(filePath));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            // each connection is 2 way: need to add both ways to HashMap
            if(connections.containsKey(line.split("-")[0]))
                connections.get(line.split("-")[0]).add(line.split("-")[1]);
            else{
                connections.put(line.split("-")[0], new ArrayList<>());
                connections.get(line.split("-")[0]).add(line.split("-")[1]);
            }
            if(connections.containsKey(line.split("-")[1]))
                connections.get(line.split("-")[1]).add(line.split("-")[0]);
            else{
                connections.put(line.split("-")[1], new ArrayList<>());
                connections.get(line.split("-")[1]).add(line.split("-")[0]);
            }
        }
    }

    public HashMap<String, List<String>> getConnections() {
        return new HashMap<>(connections);
    }
}
