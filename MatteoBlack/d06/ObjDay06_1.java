package MatteoBlack.d06;

import java.io.File;
import java.util.*;

public class ObjDay06_1 {
    public static void main (String[] args){
        ArrayList<Integer> listFish = new ArrayList<>();
        int add;
        int tempFish;
        String[] temp;

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY06/src/input.txt");
        try{
            //Fill list of StartFish
            Scanner scanner = new Scanner(file);
            temp = scanner.nextLine().split(",");
            for(int i=0; i<temp.length; i++){
                listFish.add(Integer.parseInt(temp[i]));
            }
        }catch (Exception e){
            System.err.println("Errore nella acquisizione!");
        }
        for(int day=0; day<80; day++){
            //Generate all cases, for each birth add++ and then add #fish
            add = 0;
            Iterator itr = listFish.iterator();
            for(int pos=0; itr.hasNext(); pos++){
                tempFish = (int) itr.next();
                tempFish--;
                listFish.set(pos, tempFish);
                if(tempFish==-1){
                    listFish.set(pos, 6);
                    add++;
                }
            }
            for(int i=0; i<add; i++) listFish.add(8);
        }

        System.out.println("Il risultato è: " + listFish.size());
    }
}
