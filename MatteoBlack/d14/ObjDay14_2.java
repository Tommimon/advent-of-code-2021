package MatteoBlack.d14;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ObjDay14_2 {
    public static void main(String[] arg){
        long most = 1;
        long less;
        long result;
        long tempLong;
        long[] counterAlphabet = new long[26];
        HashMap<String, Long> cacheCopy = new HashMap();
        HashMap<String, Long> cacheEmpty = new HashMap();
        HashMap<String, Long> cacheCounter = new HashMap();
        HashMap<String, String> cacheAction = new HashMap();
        String start = null;
        String tempString;
        String tempInput;
        String[] tempRules;
        Scanner scanner = null;

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY14/src/input.txt");
        try {
            scanner = new Scanner(file);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        while(scanner.hasNextLine()){
            tempInput = scanner.nextLine();
            if((!tempInput.equals(""))&&(!tempInput.contains(" -> "))){
                start = tempInput;
                for(int i=0; i<start.length(); i++) counterAlphabet[start.charAt(i)-'A']++;
            }else if(!tempInput.equals("")){
                tempRules = tempInput.split(" -> ");
                cacheAction.put(tempRules[0], tempRules[1]);
                cacheEmpty.put(tempRules[0], (long)0);
            }
        }
        cacheCounter.putAll(cacheEmpty);
        for(int i=0; i<start.length()-1; i++){
            for(String key : cacheAction.keySet()){
                if(start.substring(i, i+2).equals(key.substring(0,2))){
                    tempLong = cacheCounter.get(key);
                    cacheCounter.put(key, tempLong+1);
                }
            }
        }
        //Parte 1: 10
        for(int step=0; step<40; step++){
            cacheCopy.putAll(cacheEmpty);
            for(String key : cacheCounter.keySet()){
                if(cacheCounter.get(key)>0){
                    tempString = key.charAt(0) + cacheAction.get(key);
                    tempLong = cacheCounter.get(key) + cacheCopy.get(tempString);
                    cacheCopy.put(tempString, tempLong);
                    tempString = cacheAction.get(key) + key.charAt(1);
                    tempLong = cacheCounter.get(key) + cacheCopy.get(tempString);
                    cacheCopy.put(tempString, tempLong);
                    counterAlphabet[cacheAction.get(key).charAt(0)-'A'] += cacheCounter.get(key);
                }
            }
            cacheCounter.putAll(cacheCopy);
        }

        for(int i=0; i<counterAlphabet.length; i++) if(counterAlphabet[i]>most) most = counterAlphabet[i];
        less = most;
        for(int i=0; i<counterAlphabet.length; i++) if((counterAlphabet[i]<less)&&(counterAlphabet[i]>0)) less = counterAlphabet[i];

        result = most - less;
        System.out.println("Il risultato è: " + result);
    }
}