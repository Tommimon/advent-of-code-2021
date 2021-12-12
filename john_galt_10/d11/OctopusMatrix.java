package john_galt_10.d11;

public class OctopusMatrix {
    public static void printMatrix(Octopus m[][], int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(m[i][j].getEnergyLevel());
            }
            System.out.println("\n");
        }
    }
}
