package john_galt_10.d05;

public class Tabellone {
    private int griglia[][];

    public Tabellone() {
        griglia = new int[2000][2000];
    }

    public void updateGriglia(String s) {
        Punto partenza = new Punto(Integer.parseInt(s.split(" -> ")[0].split(",")[0]), Integer.parseInt(s.split(" -> ")[0].split(",")[1]));
        Punto arrivo = new Punto(Integer.parseInt(s.split(" -> ")[1].split(",")[0]), Integer.parseInt(s.split(" -> ")[1].split(",")[1]));
        System.out.println(partenza.toString() + " -> " + arrivo.toString());
        switch (Punto.allineamento(partenza, arrivo)) {
            case "horizontal":
                int y = partenza.getY();
                for (int x = Math.min(partenza.getX(), arrivo.getX()); x < Math.max(partenza.getX(), arrivo.getX()) + 1; x++) {
                    griglia[x][y]++;
                }
                break;
            case "vertical":
                int a = partenza.getX();
                for (int b = Math.min(partenza.getY(), arrivo.getY()); b < Math.max(partenza.getY(), arrivo.getY()) + 1; b++) {
                    griglia[a][b]++;
                }
                break;
            case "obliquo":
                int dire = 0;
                if (partenza.getX() < arrivo.getX() && partenza.getY() < arrivo.getY()) dire = 1;
                if (partenza.getX() > arrivo.getX() && partenza.getY() < arrivo.getY()) dire = 2;
                if (partenza.getX() > arrivo.getX() && partenza.getY() > arrivo.getY()) dire = 3;
                if (partenza.getX() < arrivo.getX() && partenza.getY() > arrivo.getY()) dire = 4;

                Punto p = new Punto(partenza.getX(), partenza.getY());
                while (!Punto.uguale(p, arrivo)) {
                    griglia[p.getX()][p.getY()]++;
                    p.muoviDiag(dire);
                }
                griglia[p.getX()][p.getY()]++;
                break;
        }
    }

    public int contaDue() {
        int ris = 0;
        for (int[] row : griglia) {
            for (int x : row) {
                if (x >= 2) ris++;
            }
        }
        return ris;
    }
}
