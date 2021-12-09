import java.io.File;
import java.util.*;

public class Main {
    public static void main(String args[]){
        try {
            ArrayList<Integer> fish= new ArrayList<>();
            File inputFile = new File("input6.txt");
            Scanner input = new Scanner(inputFile);
            String str = input.next();
            List<String> temp = Arrays.asList(str.split(","));
            for (int i = 0; i < temp.size(); i++) {
                fish.add(Integer.parseInt(temp.get(i)));
            }

            /*
            for(int i=0; i<fish.size(); i++){
                System.out.println(fish.get(i));
            }
            */

            int days=0;
            while (days<80) {
                int currSize=fish.size();
                for (int i = 0; i < currSize; i++) {
                    if (fish.get(i) == 0) {
                        fish.add(8);
                        fish.set(i, 6);
                    }
                    else if (fish.get(i) > 0) {
                        fish.set(i, fish.get(i)-1);
                    }
                }
                //System.out.println(fish.size());
                /*
                for(int i =0; i<fish.size(); i++){
                    System.out.print(fish.get(i)+" ");
                }
                System.out.println();

                 */
                days++;
                System.out.println(days);
            }
            System.out.println(fish.size());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
