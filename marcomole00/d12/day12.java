package marcomole00.d12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day12 {
    static HashMap<String, List<String>> nodes = new HashMap<>();
    static HashSet<List<String>> pathsFirst = new HashSet<>();
    static HashSet<List<String>> pathsSecond = new HashSet<>();


    public static boolean isLowerCase (String string) {
       return string.toLowerCase(Locale.ROOT).equals(string); // copied from tommimon, the old one was stupid and not OOP-like
   }

    public static void navigateFirstPart(List<String> pathImOn, String nodeKey){
        List<String> pathImOnClone = new ArrayList<String>(pathImOn);
         if (Objects.equals(nodeKey, "end")) {
             pathImOnClone.add("end");
             pathsFirst.add(pathImOnClone);
             return;
         }
         if (isLowerCase(nodeKey)) {
            if (pathImOnClone.contains(nodeKey)){return;} // sono già passato una volta su questo minuscolo, path illegale
         }
        pathImOnClone.add(nodeKey);
         for (String nextNode : nodes.get(nodeKey)) {
             navigateFirstPart( pathImOnClone, nextNode);
         }
    }


    public static void navigateSecondPart( List<String> pathImOn, String nodeKey) {
        List<String> pathImOnClone = new ArrayList<String>(pathImOn);
        if (Objects.equals(nodeKey, "end")) {
            pathImOnClone.add("end");
            pathsSecond.add(pathImOnClone);
            return;
        }
        if (isLowerCase(nodeKey) && pathImOnClone.contains(nodeKey) ) {
            for (String nodeOnPath: pathImOnClone) {
                if (isLowerCase(nodeOnPath) && pathImOnClone.indexOf(nodeOnPath) != pathImOnClone.lastIndexOf(nodeOnPath)) {
                    return; // In this path there are already two occurrences of a small cave
                }
            }
        }
        pathImOnClone.add(nodeKey);
        for (String nextNode : nodes.get(nodeKey)) {
            if (!Objects.equals(nextNode, "start")) {
                navigateSecondPart(pathImOnClone, nextNode);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d12/input"));
            List<String> lista = br.lines().toList();

            for (String line : lista) {
                String key = line.split("-")[0];
                String value = line.split("-")[1];
                if (!nodes.containsKey(key) ) {
                    nodes.put(key, new ArrayList<String>());
                }
                if (!nodes.containsKey(value)) {
                    nodes.put(value, new ArrayList<String>());
                }
                nodes.get(key).add(value);
                nodes.get(value).add(key);
            }
            //prints all the adjacency lists
            for (String key: nodes.keySet()) {
                System.out.print("key:"+key + " Values: ");
                nodes.get(key).forEach(s->System.out.print(s+" "));
                System.out.println();
            }
            ArrayList <String> pathImOn = new ArrayList<String>();
            pathImOn.add("start");
            for (String nextNode: nodes.get("start")) {
                navigateFirstPart( pathImOn, nextNode);
                navigateSecondPart( pathImOn, nextNode);

            }
            System.out.println(pathsFirst.size());
            System.out.println(pathsSecond.size());

        } catch (IOException e) {System.out.println(e);}
    }
}
