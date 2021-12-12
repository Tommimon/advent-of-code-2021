package marcomole00.d12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day12 {
   public static boolean isLowerCase (String string) {
       for (int i =0; i<string.length();i++) {
           if (Character.isUpperCase(string.charAt(i))) {return false;}
       }
       return true;
   }

    public static void navigateFirstPart(HashSet<List<String>> paths, List<String> pathImOn, String nodeKey,  HashMap<String, List<String>> nodes) {
        List<String> pathImOnClone = new ArrayList<String>(pathImOn);
         if (Objects.equals(nodeKey, "end")) {
             pathImOnClone.add("end");
             paths.add(pathImOnClone);

             return;
         }

         if (isLowerCase(nodeKey)) {
            if (pathImOnClone.contains(nodeKey)){return;} // sono già passato una volta su questo minuscolo, path illegale
         }

        pathImOnClone.add(nodeKey);
         for (String nextNode : nodes.get(nodeKey)) {

             navigateFirstPart(paths, pathImOnClone, nextNode, nodes);
         }
    }


    public static void navigateSecondPart(HashSet<List<String>> paths, List<String> pathImOn, String nodeKey,  HashMap<String, List<String>> nodes) {
        List<String> pathImOnClone = new ArrayList<String>(pathImOn);


        if (Objects.equals(nodeKey, "end")) {
            pathImOnClone.add("end");
            paths.add(pathImOnClone);
            return;
        }

        if (isLowerCase(nodeKey) && pathImOnClone.contains(nodeKey) ) {
            for (String nodeOnPath: pathImOnClone)
            {
                if (isLowerCase(nodeOnPath) && pathImOnClone.indexOf(nodeOnPath) != pathImOnClone.lastIndexOf(nodeOnPath)) {
                    return; // In this path there are already two occurrences of a small cave
                }
            }
        }

        pathImOnClone.add(nodeKey);
        for (String nextNode : nodes.get(nodeKey)) {
            if (!Objects.equals(nextNode, "start")) {
                navigateSecondPart(paths, pathImOnClone, nextNode, nodes);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d12/input"));
            List<String> lista = br.lines().toList();
            HashMap<String, List<String>> nodes = new HashMap<>();
            HashSet<List<String>> pathsFirst = new HashSet<>();
            HashSet<List<String>> pathsSecond = new HashSet<>();

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
            //prints all the adjacency lists(?)
            for (String key: nodes.keySet()) {
                System.out.print("key:"+key + " Values: ");
                nodes.get(key).forEach(s->System.out.print(s+" "));
                System.out.println();
            }
            ArrayList <String> pathImOn = new ArrayList<String>();
            pathImOn.add("start");
            for (String nextNode: nodes.get("start")) {
                navigateFirstPart(pathsFirst, pathImOn, nextNode, nodes);
                navigateSecondPart(pathsSecond, pathImOn, nextNode, nodes);

            }
            System.out.println(pathsFirst.size());
            System.out.println(pathsSecond.size());



         /*   for (List<String> path : pathsSecond)
            {
                path.forEach(s -> System.out.print(s + ","));
                System.out.println();
            }*/

        } catch (IOException e) {System.out.println(e);}
    }
}
