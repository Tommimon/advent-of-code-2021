package Gonduls.d12;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Input {

    private final HashMap<String, List<String>> connections = new HashMap<>();

    public Input(String filePath) throws IOException {
        Scanner scan = new Scanner(new File(filePath));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
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
        HashMap<String, List<String>> copy = new HashMap<>();

        for (String key: connections.keySet()) {
            copy.put(key, new ArrayList<>(connections.get(key)));
        }
        return copy;
    }
}
