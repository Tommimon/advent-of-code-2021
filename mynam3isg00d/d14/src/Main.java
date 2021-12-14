package mynam3isg00d.d14.src;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        InpVal inp = r.parseInput("input.txt");
        inp.solve(10);
        System.out.println("Part 1: " + inp.getResult());

        InpVal inp2 = r.parseInput("input.txt");
        inp2.solve(40);
        System.out.println("Part 2: " + inp2.getResult());
    }
}
