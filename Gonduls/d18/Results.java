package Gonduls.d18;

import java.io.IOException;
import java.util.List;

public class Results {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d18/input.txt");

        List<String> lines = input.getInput();
        String current = lines.get(0);

        for(String s : lines.subList(1, lines.size())) {
            current = Pair.reduction(Pair.combine(current, s));
        }
        System.out.println("Result part 1 = " + Pair.magnitude(current));

        int max = 0;
        for(int i = 0; i < lines.size() - 1; i++){
            for(int j = i +1; j < lines.size(); j++){
                max = Math.max(max, Pair.magnitude(Pair.reduction(Pair.combine(lines.get(i) , lines.get(j)))));
                max = Math.max(max, Pair.magnitude(Pair.reduction(Pair.combine(lines.get(j) , lines.get(i)))));
            }
        }
        System.out.println("Result part 2 = " + max);
    }
}
