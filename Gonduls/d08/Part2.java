package Gonduls.d08;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Part2 {
    public static void main(String[] args) throws IOException {
        Stream<String> stream = Files.lines(Paths.get("Gonduls/d08/input.txt"));
        int result = stream.map(Part2::giveOutput).reduce(0, Integer::sum);
        stream.close();
        System.out.println(result);
    }

    private static int giveOutput(String input){
        String[] parts = input.replace(" | ", "z").split("z");
        String[] numbers = input.replace(" | ", " ").split(" ");
        final String[] conversion = new String[10];

        for(int i = 0; i <numbers.length; i++){
            numbers[i] = sortString(numbers[i]);
            switch (numbers[i].length()) {
                case 2 -> conversion[1] = numbers[i];
                case 3 -> conversion[7] = numbers[i];
                case 4 -> conversion[4] = numbers[i];
                case 7 -> conversion[8] = numbers[i];
            }
        }

        for(String st : numbers){
            if(st.length() == 6){
                boolean is9 = true;
                for(char c : conversion[4].toCharArray()){
                    if(st.indexOf(c) == -1) {
                        is9 = false;
                        break;
                    }
                }

                if(is9)
                    conversion[9] = st;

            } else if(st.length() == 5){
                boolean is5 = true;
                for(char c : conversion[4].toCharArray()){
                    if(st.indexOf(c) == -1 && conversion[1].indexOf(c) == -1) {
                        is5 = false;
                        break;
                    }
                }
                if(is5)
                    conversion[5] = st;
            }
        }

        for(String st : numbers){
            if(st.length() == 6){
                boolean is6 = true;
                boolean is0 = false;

                if(st.startsWith(conversion[9]))
                    continue;

                for(char c : conversion[5].toCharArray()){
                    if(st.indexOf(c) == -1) {
                        is6 = false;
                        is0 = true;
                        break;
                    }
                }
                if(is0)
                    conversion[0] = st;
                if(is6)
                    conversion[6] = st;

            } else if(st.length() == 5){
                boolean is3 = true;
                boolean is2 = false;

                if(st.startsWith(conversion[5]))
                    continue;

                for(char c : st.toCharArray()){
                    if(conversion[9].indexOf(c) == -1){
                        is3 = false;
                        is2 = true;
                        break;
                    }
                }

                if(is2)
                    conversion[2] = st;
                if(is3)
                    conversion[3] = st;
            }
        }

        int result = 0;
        for(String st : (parts[1].split("\s"))){
            st = sortString(st);
            result = 10*result + Arrays.asList(conversion).indexOf(st);
        }

        return result;
    }

    private static String sortString(String input){
        char[] temp = input.toCharArray();
        Arrays.sort(temp);
        return(new String(temp));
    }
}
