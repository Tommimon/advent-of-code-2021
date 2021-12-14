package mynam3isg00d.d14.src;

import java.util.Arrays;
import java.util.HashMap;

public class InpVal {
    String polymer;
    HashMap<String, String> rules;
    HashMap<String, Long> count;
    Long[] alphabet;

    InpVal() {
        rules = new HashMap<>();
        count = new HashMap<>();
        alphabet = new Long[26];
        Arrays.fill(alphabet, 0L);
    }

    public void printInfo() {
        System.out.println("Polymer: " + this.polymer);
        System.out.println("Rules: " + this.rules);
        System.out.println("Count: " + this.count);
        System.out.print("Alphabet: {");
        for(int i=0; i<25; i++) {
            System.out.print("" + (char)(i+65) + "=" + alphabet[i] + ", ");
        }
        System.out.print("" + (char)(25+65) + "=" + alphabet[25] + "}\n");
    }

    public long getResult() {
        long max = 0;
        long min = 9000000000000000000L;
        for(int i=0; i<26; i++) {
            if(alphabet[i] != 0) {
                max = Math.max(max, alphabet[i]);
                min = Math.min(min, alphabet[i]);
            }
        }
        return (max - min);
    }

    public void init() {
        for(int i=0; i<polymer.length() - 1; i++) {
            alphabet[polymer.charAt(i) - 65]++;
            String toAdd = polymer.charAt(i) + "" + polymer.charAt(i+1);
            count.put(toAdd, count.get(toAdd) + 1);
        }
        alphabet[polymer.charAt(polymer.length() - 1) - 65]++;
    }

    public void solve(int reps) {
        for(int k=0; k<reps; k++) {
            //System.out.print("\n\nRep: " + (k+1) + "\n");
            HashMap<String, Long> countCopy = new HashMap<>();
            countCopy.putAll(count);
            for(String key : count.keySet()) {
                long n = count.get(key);
                //System.out.println("DEBUG Key: " + key);
                countCopy.put(key, countCopy.get(key) - n);
                String value = rules.get(key);
                //System.out.println("\tDEBUG value: " + value);
                alphabet[value.charAt(0) - 65] += n;
                String rule1 = key.charAt(0) + "" + value.charAt(0);
                String rule2 = value.charAt(0) + "" + key.charAt(1);
                //System.out.println("\tDEBUG Rule 1&2: " + rule1 + " | " + rule2);
                countCopy.put(rule1, countCopy.get(rule1) + n);
                countCopy.put(rule2, countCopy.get(rule2) + n);
            }
            count.putAll(countCopy);
            //System.out.println(count);
        }
    }
}