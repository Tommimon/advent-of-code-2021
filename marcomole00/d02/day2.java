package marcomole00.d02;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.List;

public class day2 {

    public static int parsing(List<String> lista, String direction){
        return lista.stream().filter(s -> s.startsWith(direction)).map(s -> s.replaceAll("[^0-9]", "")).map(s -> Integer.parseInt(s)).reduce(0,(n,b) -> n+b);
    }

    public static void main(String[] args) {
         int vertical = 0;
         int  horizontal = 0;
       try {
           BufferedReader br = Files.newBufferedReader(Paths.get("marcomole00/d02/input"));
           List<String>  lista= br.lines().toList();
           //part1
           vertical = -parsing(lista,"u") + parsing(lista,"d");
           horizontal = parsing(lista,"f");

           System.out.println(vertical*horizontal);

           //part2
           int aim = 0;
           vertical =0;
           horizontal = 0;
           int x = 0;
           for(String i : lista)
           {
               if (i.startsWith("u")) {aim -= Integer.parseInt( i.replaceAll("[^0-9]", ""));}
               if (i.startsWith("d")) {aim += Integer.parseInt( i.replaceAll("[^0-9]", ""));}
               if (i.startsWith("f"))
               {
                x = Integer.parseInt( i.replaceAll("[^0-9]", ""));
                horizontal += x;
                vertical += aim*x;
               }
               System.out.println("aim " + aim + " v " + vertical + " h " + horizontal);

           }

           System.out.println(vertical*horizontal);








       }
       catch (IOException e) {
           System.out.println("bello di mamma");
        }
       }
    }

