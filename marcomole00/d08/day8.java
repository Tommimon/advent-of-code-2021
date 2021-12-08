package marcomole00.d08;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


// uno ha due segmenti
// quattro ha quattro segmenti
// sette ha tre segmenti
// otto ha sette segmenti
public class day8 {
    public  static char stringDiff (String len3 , String len2)
    {

        List<Character> duce3 = new ArrayList<>();
        List<Character> duce2 = new ArrayList<>();
        IntStream.range(0, len3.length()).mapToObj(i -> len3.toCharArray()[i]).forEach(duce3::add);
        IntStream.range(0, len2.length()).mapToObj(i -> len2.toCharArray()[i]).forEach(duce2::add);
        duce3.removeAll(duce2);
        return duce3.get(0);
    }

    public static int sevenDigitNumber(String input)
    {
        switch (input.length())
        {
            case 2: return 1;
            case 4: return 4;
            case 3: return 7;
            case 7: return 8;
            case 5:
                if (input.contains("b")){return 5;}
                else if (input.contains("e")) {return 2;}
                else return 3;
            case 6:
               if (!input.contains("d")) {return 0;}
               else if (input.contains("e")){return 6;}
               else if (input.contains("c")) {return 9;}
        }
        return -1;
    }



    public static void main(String[] args) {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d08/input"));
            List<String> input = br.lines().toList();
            List<String[]> connection =  input.stream().map(s-> s.replace("|", "x"))
                    .map(s -> s.split("x")[0]).map(s -> s.split(" ")).toList();
            List<String[]> display =  input.stream().map(s-> s.replace("|", "x"))
                    .map(s -> s.split("x")[1]).map(s -> s.split(" ")).toList();


            int counter =0,indice =0;
            int[] numberOfAppearances;
            char[] mapping; // mapping[wrongChar] = rightChar
            String one = "", four= "", seven = "", eight="";
            System.out.println("a b c d e f g");
            char[][] mappingAllLines = new char[connection.size()][7];
            for (String[] line : connection){
                numberOfAppearances = new int[7];
                //mapping = new char[7];

                for (String digit : line) {
                    switch (digit.length()) {
                        case 1 -> one = new String(digit);
                        case 3 -> seven = new String(digit);
                        case 4 -> four = new String(digit);
                        case 7 -> eight = new String(digit);
                    }

                    for(char letter: digit.toCharArray())
                    {
                        numberOfAppearances[letter-'a']++;
                    }
                }
                mappingAllLines[indice][stringDiff(seven,one)-'a'] = 'a';

                for(int i = 0; i<7; i++)
                {
                    switch (numberOfAppearances[i]){
                        case 4:
                            mappingAllLines[indice][i] = 'e';
                            break;
                        case 6:
                            mappingAllLines[indice][i] = 'b';
                            break;
                        case 7:
                            if (four.indexOf(( i + 'a') ) >=0) {mappingAllLines[indice][i] = 'd';}
                            else mappingAllLines[indice][i] = 'g';
                            break;
                        case 8:
                            if (i != stringDiff(seven,one) - 'a'){mappingAllLines[indice][i] = 'c';}
                            break;
                        case 9:
                            mappingAllLines[indice][i] = 'f';
                            break;


                    }
                }

              //  for(char k: mapping)
               // {
                  //  System.out.print(k + " ");
               // }
                //System.arraycopy(mapping, 0, mappingAllLines[indice], 0, 7);


                System.out.println();

                Arrays.stream(numberOfAppearances).forEach(s->System.out.print(s+" "));
                System.out.println();
            indice++;
            }




            indice =0;
            int ao= 0;
            int aoSummed = 0;
            char[] temp;
            for (String[] f : display){

                ao = 0;
                for (String a : f)
                {
                    if (a == "") { continue;}
                    if (a.length() ==2 || a.length() == 3 || a.length() == 4 || a.length() == 7){counter++;}
                    temp = new char[a.length()];


                    for (int i = 0;i< a.length();i++)
                    {
                        temp[i]  = mappingAllLines[indice][a.toCharArray()[i] -'a'];
                        //System.out.print(temp[i] + " ");


                    }
                    //System.out.print(sevenDigitNumber(String.valueOf(temp)));
                    Arrays.sort(temp);
                    ao = ao*10 + sevenDigitNumber(String.valueOf(temp));

                }
                System.out.println(ao);
                aoSummed += ao;
                indice++;
            }

            System.out.println(counter);
            System.out.println(aoSummed);

        } catch (IOException ignored){}
    }
}
