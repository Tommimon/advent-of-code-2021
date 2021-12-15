package marcomole00.d14;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class day14 {
    public static void main(String[] args) {

        try {
            String input = "marcomole00/d14/input";
            BufferedReader br = Files.newBufferedReader(Paths.get(input));

            String firstLine = br.readLine();
            HashMap<String, String> productions = new HashMap<>();
            HashMap<String, Long> counter = new HashMap<>();
            HashMap<String, Long> tempCounter = new HashMap<>();
            HashMap<String, Long> letterCounter = new HashMap<>();
            HashMap<String, Long> emptyTemplate =new HashMap<>();


            br.lines().filter(s -> s.matches("[A-Z][A-Z] -> [A-Z]")).map(s->s.split(" -> ")).forEach(s -> productions.put(s[0], s[1]));
            System.out.println(firstLine);
            for (int i = 0; i<firstLine.length()-1;i++) {
                String biLetteral = String.valueOf(firstLine.charAt(i)) + firstLine.charAt(i + 1);
                if (!counter.containsKey(biLetteral))
                {
                    counter.put(biLetteral,0L);
                    emptyTemplate.put(biLetteral,0L);
                }
              counter.put(biLetteral,counter.get(biLetteral)+1);
            }

            for (int i =0; i<firstLine.length();i++) {
                letterCounter.compute(String.valueOf(firstLine.charAt(i)), (k, v) -> v==null? 1L: v+1); // count the letters for the assignment

            }

            for (int i =0; i<40; i++) {

                for (String key : counter.keySet()) {
                   if (counter.get(key) == 0){continue;}
                   long numberOfBiLetteral = counter.get(key);
                    counter.put(key,0L);
                    String prod1 = key.charAt(0) + productions.get(key); // create prod1
                    String prod2 =  productions.get(key) + key.charAt(1); // create prod2
                  

                    letterCounter.compute(productions.get(key), (k,v) -> v== null ? numberOfBiLetteral: v+numberOfBiLetteral); // count the letters for the assignment
                    tempCounter.compute(prod1, (k,v) -> v== null? numberOfBiLetteral : v+numberOfBiLetteral );
                    tempCounter.compute(prod2, (k,v) -> v== null? numberOfBiLetteral : v+numberOfBiLetteral );
                }

                counter = new HashMap<>(tempCounter);
                tempCounter = new HashMap<>(emptyTemplate);


            }

            for (String key : letterCounter.keySet()) {
                System.out.println("key=" + key +"  value="+ letterCounter.get(key) );

            }

            long  max = letterCounter.values().stream().max(Long::compareTo).get();
            long min = letterCounter.values().stream().min(Long::compareTo).get();
          System.out.println(max -min);


        } catch (IOException ignored){}
    }
}
