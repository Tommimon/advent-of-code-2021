package Gonduls.d16;

import java.io.IOException;

public class Results {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d16/input.txt");

        // everything is managed outside "main"
        PacketManager manager = new PacketManager(input.getOutput());
        System.out.println("Result part 1 = " + manager.sumVersion());
        System.out.println("Result part 2 = " + manager.getValue());
    }
}
