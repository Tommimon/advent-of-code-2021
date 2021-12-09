import java.io.File;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        try {
            ArrayList<Integer> positions = new ArrayList<>();
            ArrayList<Long> toCompare = new ArrayList<>();
            File inputFile = new File("input7.txt");
            Scanner input = new Scanner(inputFile);
            String str = input.next();
            List<String> temp = Arrays.asList(str.split(","));
            for (int i = 0; i < temp.size(); i++) {
                positions.add(Integer.parseInt(temp.get(i)));
            }

            for(int i=0; i<positions.size(); i++){
                long counter=0;
                for(int j=0; j<positions.size(); j++){
                    if(j!=i){
                        for(int k=1; k<=Math.abs(positions.get(i)-positions.get(j)); k++){
                            counter+=k;
                        }
                    }
                }
                toCompare.add(counter);
            }

            long smallest=toCompare.get(0);
            for(int i=0; i<toCompare.size(); i++){
                if(toCompare.get(i)<smallest){
                    smallest=toCompare.get(i);
                }
            }

            System.out.println(smallest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}