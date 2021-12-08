package MatteoBlack.d06;
import java.io.File;
import java.util.*;

//Special thanks go to my lovely @Pier to inspire my code, it's not a shameless ctrl+c ctrl+v
public class ObjDay06_2 {
    public static void main (String[] args){
        long result;
        int indexZero;
        long[] birth = new long[9];
        String[] temp;

        File file = new File("/Users/matteoblack/Desktop/AOC_2021/DAY06/src/input.txt");
        try{
            //Fill array (circular) with the days left before a new birth
            Scanner scanner = new Scanner(file);
            temp = scanner.nextLine().split(",");
            for(int i=0; i<temp.length; i++){
                birth[Integer.parseInt(temp[i])]++;
            }
        }catch (Exception e){
            System.err.println("Errore nella acquisizione!");
        }

        //Start the calendar and count new birth
        indexZero = 0;
        for(int day=1; day<=256; day++){
            birth[(indexZero+7)%9] += birth[indexZero];
            indexZero = (indexZero+1)%9;
        }

        //Evaluate the result
        result = 0;
        for(int i=0; i<9; i++) result += birth[i];
        System.out.println("Il risultato è: " + result);
    }
}
