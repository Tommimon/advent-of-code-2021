package john_galt_10.d02;

public class Sottomarino {
    private int horizontal;
    private int depth;

    public Sottomarino() {
        horizontal = 0;
        depth = 0;
    }

    public void move(String input) {
        String[] splittati = new String[2];
        splittati = input.split(" ");
        String direction = splittati[0];
        int quantity = Integer.parseInt(splittati[1]);

        switch (direction) {
            case "forward": horizontal += quantity;
                            break;
            case "up": depth -= quantity;
                        break;
            case "down": depth += quantity;
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
