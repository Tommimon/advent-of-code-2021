package tommimon.d01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class myClass {
    public static void main(String[] args) throws FileNotFoundException {
        Vector<Integer> nums = new Vector<>();
        File myObj = new File("tommimon/d01/input");
        Scanner myReader = new Scanner(myObj);
        while(myReader.hasNextLine()) {
            String data = myReader.nextLine();
            nums.add(Integer.parseInt(data));
        }
        myReader.close();

        int counter = 0;
        for (int i = 1; i < nums.size(); i++) {
            if(nums.get(i) > nums.get(i-1)) {
                counter++;
            }
        }
        System.out.println(counter);

        counter = 0;
        for (int i = 3; i < nums.size(); i++) {
            if(nums.get(i) > nums.get(i-3)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
