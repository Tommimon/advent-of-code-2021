public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        String[] instSet = r.parseInput("input.txt");

        int x, y;
        x = y = 0;
        for(String s : instSet) {
            if (s.contains("forward")) {
                x += Integer.parseInt(s.split(" ")[1]);
            }
            if (s.contains("down")) {
                y += Integer.parseInt(s.split(" ")[1]);
            }
            if (s.contains("up")) {
                y -= Integer.parseInt(s.split(" ")[1]);
            }
        }

        System.out.println("Part 1: " + (x*y));

        int aim = 0;
        x = y = 0;
        for(String s : instSet) {
            if (s.contains("forward")) {
                x += Integer.parseInt(s.split(" ")[1]);
                y += aim * Integer.parseInt(s.split(" ")[1]);
            }
            if (s.contains("down")) {
                aim += Integer.parseInt(s.split(" ")[1]);
            }
            if (s.contains("up")) {
                aim -= Integer.parseInt(s.split(" ")[1]);
            }
        }

        System.out.println("Part 2: " + (x*y));
    }
}
