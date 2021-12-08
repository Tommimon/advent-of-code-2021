package MatteoBlack.d07;

import java.io.File;
import java.util.*;

public class ObjDay07 {
    public static void main(String[] args){
        int max;
        long fuel1;
        long fuel2;
        long bestFuel1;
        long bestFuel2;
        int tempNum;
        String[] temp;
        ArrayList<Integer> listNum = new ArrayList<>();

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY07/src/input.txt");
        try {
            //Save horizontal positions
            Scanner scanner = new Scanner(file);
            temp = scanner.nextLine().split(",");
            for(int i=0; i<temp.length; i++) listNum.add(Integer.parseInt(temp[i]));
        }catch (Exception e){
            System.err.println("Errore File/Input");
        }

        //Search Max
        max = 0;
        Iterator itr = listNum.iterator();
        while(itr.hasNext()){
            tempNum = (int) itr.next();
            if(tempNum>max) max = tempNum;
        }

        //BestFuel
        bestFuel1 = 999999999;
        bestFuel2 = bestFuel1;
        for(int c=0; c<max; c++){
            fuel1 = 0;
            fuel2 = fuel1;
            itr = listNum.iterator();
            while (itr.hasNext()){
                tempNum = (int) itr.next();
                fuel1 += Math.abs(c - tempNum);
                fuel2 += (Math.abs(c - tempNum)*(Math.abs(c - tempNum)+1))/2;
            }
            if(bestFuel1>fuel1) bestFuel1 = fuel1;
            if(bestFuel2>fuel2) bestFuel2 = fuel2;
        }

        System.out.println("Il risultato parte 1 è: " + bestFuel1);
        System.out.println("Il risultato parte 2 è: " + bestFuel2);
    }
}
