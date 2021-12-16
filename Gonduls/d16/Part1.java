package Gonduls.d16;

import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d16/test.txt");

        String binaryString = input.getOutput();

        System.out.println(sumVersion(binaryString));
    }

    private static int sumVersion(String binary){
        int result = 0;
        return result;
    }
}
