//
// Day 12 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 5457
// Second part solution: 128506
//

package riccardo_negri.d12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d12 {
    public static void main (String[] args) {
        File f = new File("riccardo_negri/d12/input.txt");
        Scanner reader = null;
        HashMap<String, Cave> map = new HashMap<>();

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        boolean toAdd;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] temp = line.split("-");
            // first cave
            toAdd = true;
            for (String c : map.keySet()) {
                if (c.equals(temp[0])) {
                    toAdd = false;
                    map.get(temp[0]).addNearCave(temp[1]);
                }
            }
            if (toAdd) {
                map.put(temp[0], new Cave(temp[0]));
                map.get(temp[0]).addNearCave(temp[1]);
            }
            // second cave
            toAdd = true;
            for (String c : map.keySet()) {
                if (c.equals(temp[1])) {
                    toAdd = false;
                    map.get(temp[1]).addNearCave(temp[0]);
                }
            }
            if (toAdd) {
                map.put(temp[1], new Cave(temp[1]));
                map.get(temp[1]).addNearCave(temp[0]);
            }
        }

        Set<String> paths = new HashSet<>();
        findPaths(paths, "start", map, List.of("start"), List.of("start"), true);
        System.out.println(paths.size());

        paths = new HashSet<>();
        findPaths(paths, "start", map, List.of("start"), List.of("start"), false);
        System.out.println(paths.size());
    }

    public static void findPaths (Set<String> paths, String s, HashMap<String, Cave> map, List<String> smallVisited, List<String> path, boolean extraTime) {
        for (String c : map.get(s).getNearCaves()) {
            if (!smallVisited.contains(c)) {
                List<String> currPath = new ArrayList<>(path);
                currPath.add(c);
                List<String> currSmallVisited = new ArrayList<>(smallVisited);
                if (c.toLowerCase().equals(c)) {
                    if (c.equals("end")) {
                        paths.add(path + "end");
                    }
                    else if (extraTime) {
                        currSmallVisited.add(c);
                        findPaths(paths, c, map, currSmallVisited, currPath, true);
                    }
                    else {
                        findPaths(paths, c, map, currSmallVisited, currPath, true);
                        currSmallVisited.add(c);
                        findPaths(paths, c, map, currSmallVisited, currPath, false);
                    }
                }
                else {
                    findPaths(paths, c, map, currSmallVisited, currPath, extraTime);
                }
            }
        }
    }

}

