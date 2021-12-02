package tommimon.d02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class SimpleSolution {
    public static void main(String[] args) throws FileNotFoundException {
        Vector<String> nums = new Vector<>();
        File myObj = new File("tommimon/d02/input");
        Scanner myReader = new Scanner(myObj);
        while(myReader.hasNextLine()) {
            String data = myReader.nextLine();
            nums.add(data);
        }
        myReader.close();

        int x = 0;
        int z = 0;

        for (String num : nums) {
            String[] command = num.split(" ");
            if (Objects.equals(command[0], "forward")) {
                x += Integer.parseInt(command[1]);
            }
            if (Objects.equals(command[0], "down")) {
                z += Integer.parseInt(command[1]);
            }
            if (Objects.equals(command[0], "up")) {
                z -= Integer.parseInt(command[1]);
            }
        }
        System.out.println(x*z);

        x = 0;
        z = 0;
        int aim = 0;

        for (String num : nums) {
            String[] command = num.split(" ");
            if (Objects.equals(command[0], "forward")) {
                x += Integer.parseInt(command[1]);
                z += aim * Integer.parseInt(command[1]);
            }
            if (Objects.equals(command[0], "down")) {
                aim += Integer.parseInt(command[1]);
            }
            if (Objects.equals(command[0], "up")) {
                aim -= Integer.parseInt(command[1]);
            }
        }
        System.out.println(x*z);
    }
}
