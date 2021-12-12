package Gonduls.d12;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        Input input = new Input("Gonduls/d12/input.txt");

        HashMap<String, List<String>> connections = input.getConnections();
        Crawler crawler = new Crawler(connections);

        System.out.println("Result part 1 = " + crawler.getResult1());
        System.out.println("Result part 2 = " + crawler.getResult2());
    }
}
