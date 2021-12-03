package MatteoBlack.d03;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*  FilePath
 *      testPath = "/Users/matteoblack/Desktop/AOC_2021/DAY03/src/test.txt"
 *      inputPath = "/Users/matteoblack/Desktop/AOC_2021/DAY03/src/input.txt"
 */

public class ObjDay03_2 {
    private static void filtered(ArrayList<String> list, char most, char least){
        int c;
        int sum;
        int counterLine;
        char filter;
        boolean stop;
        String temp;

        c = 0;
        stop = true;
        while(stop){
            sum = 0;
            counterLine = 0;
            Iterator itr = list.iterator();
            while(itr.hasNext()){
                counterLine++;
                temp = (String) itr.next();
                if(temp.charAt(c)=='1') sum++;
            }
            filter = least;
            if(sum>=(counterLine+1)/2) filter = most;
            itr = list.iterator();
            while(itr.hasNext()){
                temp = (String) itr.next();
                if(temp.charAt(c)!=filter){
                    itr.remove();
                }
            }
            if(list.size()==1){
                stop=false;
            }
            c++;
        }
    }

    public static void main(String[] args){
        int result;
        String line;
        String co2 = "";
        String oxy = "";
        ArrayList<String> listOxy = new ArrayList<String>();
        ArrayList<String> listCo2 = new ArrayList<String>();

        try {
            //Pointer File
            File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY03/src/input.txt");
            //Obj to read the File
            Scanner scanner = new Scanner(file);

            //Fill ArratList
            line = scanner.nextLine();
            listCo2.add(line);
            listOxy.add(line);
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                listCo2.add(line);
                listOxy.add(line);
            }

            //FilterCo2
            filtered(listCo2, '0', '1');
            co2 = listCo2.get(0);

            //FilterOxy
            filtered(listOxy, '1', '0');
            oxy = listOxy.get(0);

            //Result
            result = Integer.parseInt(oxy, 2) * Integer.parseInt(co2, 2);
            System.out.println("Il risultato è: " + result);
        }catch (Exception e){
            System.err.println("Error Scanner!!");
        }
    }
}
