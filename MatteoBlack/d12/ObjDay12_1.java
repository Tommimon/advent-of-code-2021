package MatteoBlack.d12;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjDay12_1 {
    //Method to expand the path
    private static int path(ArrayList<String[]> list, String start, ArrayList<String> situation){
        int numPath = 0;
        boolean step = false;
        boolean skip = false;
        String next = null;
        ArrayList<String> copy;
        copy = (ArrayList<String>) situation.clone();
        //Exit condiction
        if(start.equals("end")){
            return 1;
        }
        //Checking for lowercase; Search the next step in all the possibility
        if((start.compareTo("aa")>=0)&&(start.compareTo("zz")<=0)) copy.add(start);
        for(int i=0; i<list.size(); i++){
            if((list.get(i)[0].equals(start))&&(!list.get(i)[1].equals("start"))){
                step = true;
                next = list.get(i)[1];
            }else if((list.get(i)[1].equals(start))&&(!list.get(i)[0].equals("start"))){
                step = true;
                next = list.get(i)[0];
            }
            if(step){
                //Check for double elem
                if((next.compareTo("aa")>=0)&&(next.compareTo("zz")<=0)&&(!next.equals("end"))){
                    for(int c=0; c<copy.size(); c++){
                        if(copy.get(c).equals(next)){
                            skip = true;
                            break;
                        }
                    }
                }
                if(!skip) numPath += path(list, next, copy);
            }
            skip = false;
            step = false;
        }
        return numPath;
    }
    public static void main(String[] arg){
        int result1 = 0;
        Scanner scanner = null;
        ArrayList<String> situation;
        ArrayList<String[]> connection = new ArrayList<>();
        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY12/src/input.txt");
        try{
            scanner = new Scanner(file);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        while(scanner.hasNextLine()){
            connection.add(scanner.nextLine().split("-"));
        }
        //Searching starting point
        for(int i=0; i<connection.size(); i++){
            if(connection.get(i)[0].equals("start")){
                situation = new ArrayList<>();
                result1 += path(connection, connection.get(i)[1], situation);
            }else if(connection.get(i)[1].equals("start")){
                situation = new ArrayList<>();
                result1 += path(connection, connection.get(i)[0], situation);
            }
        }

        System.out.println("Il risultato della parte 1 è: " + result1);
    }
}
