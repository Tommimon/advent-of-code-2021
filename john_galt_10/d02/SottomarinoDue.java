package john_galt_10.d02;

public class SottomarinoDue {
    private int horizontal;
    private int depth;
    private int aim;

    public SottomarinoDue() {
        horizontal = 0;
        depth = 0;
        aim = 0;
    }

    public void move(String input) {
        String[] splittati = new String[2];
        splittati = input.split(" ");
        String direction = splittati[0];
        int quantity = Integer.parseInt(splittati[1]);

        switch (direction) {
            case "forward": horizontal += quantity;
                depth += aim*quantity;
                break;
            case "up": aim -= quantity;
                break;
            case "down": aim += quantity;
                break;
        }
    }

    public int getDepth() {
        return depth;
    }

    public int getHorizontal() {
        return horizontal;
    }
}
