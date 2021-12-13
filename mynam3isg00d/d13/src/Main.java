package mynam3isg00d.d13.src;

public class Main {
    public static void main(String[] args) {
        Reader r = new Reader();
        InputType it = r.parseInput("input.txt");

        for(Integer f : it.folds) {
            it.fold(f);
            if (f == it.folds.get(0)) System.out.println("Part 1: " + it.points.size());
        }

        char[][] paper = new char[6][39];
        for(int i=0; i<6; i++) {
            for(int j=0; j<39; j++) {
                paper[i][j] = '.';
            }
        }

        for(Integer[] p : it.points) {
            paper[p[1]][p[0]] = '#';
        }

        System.out.println("Part 2: ");
        for(int i=0; i<6; i++) {
            for(int j=0; j<39; j++) {
                System.out.print(paper[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
