package tommimon.d01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Reports{
    Vector<Integer> nums;

    public Iterator<Integer> window(int size) {
        return new Window(this, size);
    }

    public Reports(String fileName)  {
        nums = new Vector<>();
        try {
            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                nums.add(Integer.parseInt(line));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int get(int i) {
        return nums.get(i);
    }

    public int size() {
        return nums.size();
    }
}
