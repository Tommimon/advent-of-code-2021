package Gonduls.d11;

import java.io.IOException;

public class Part1 {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d11/input.txt");
        DumboOctopuses dumbos = new DumboOctopuses(input.getDumbos());

        int result = 0;
        int i;
        for( i = 0; i< 100; i++){
            result += dumbos.passStep();
        }

        System.out.println("Result part 1 = " + result);

        // Laziness kickin' in:
        String allFlash = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]\n";
        allFlash = allFlash + allFlash + allFlash + allFlash + allFlash;
        allFlash = allFlash + allFlash;

        while (!dumbos.toString().equals(allFlash)){
            i++;
            dumbos.passStep();
        }

        System.out.println("Result part 2 = " + i);
    }
}
