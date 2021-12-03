//Some spaghetti for you (terrrible terrible dreadful messy solution)
package marcoparadina.d03._1;
import java.io.*;
import java.util.*;

public class Main {
    private static int zeroCounter=0;
    private static int oneCounter=0;
    private static ArrayList<Integer> gammaArr = new ArrayList<Integer>();
    private static ArrayList<Integer> epsilonArr = new ArrayList<Integer>();

    public static void main(String args[]){
        try {
            File inputFile = new File("input3.txt");
            Scanner input = new Scanner(inputFile);
            int i = 0;

            for(int j=0; j<input.next().length(); j++){


                File inputFileAlias = new File("input3.txt");
                Scanner inputAlias = new Scanner(inputFile);
                while (inputAlias.hasNext()) {

                    char currChar=inputAlias.next().charAt(j);


                        if(Character.getNumericValue(currChar)==1) {
                            oneCounter++;
                        }
                        else {
                            zeroCounter++;
                        }
                }
                if (oneCounter > zeroCounter) {
                    gammaArr.add(1);
                    epsilonArr.add(0);

                } else {
                    gammaArr.add(0);
                    epsilonArr.add(1);

                }

                oneCounter = 0;
                zeroCounter = 0;
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }
        int exponent=0;
        int decimal=0;
        for(int i=gammaArr.size()-1; i>=0; i--){
            decimal+=Math.pow(2,exponent)*gammaArr.get(i);
            exponent++;
        }
        int gamma=decimal;

        decimal=0;
        exponent=0;
        for(int i=epsilonArr.size()-1; i>=0; i--){
            decimal+=Math.pow(2,exponent)*epsilonArr.get(i);
            exponent++;
        }
        int epsilon=decimal;

        long result=gamma*epsilon;

        System.out.println(result);
    }
}