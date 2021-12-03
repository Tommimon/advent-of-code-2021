package john_galt_10.d03;

public class Occorrenze {
    private int[] occorrenzeBit;

    public Occorrenze() {
        occorrenzeBit = new int[12];
    }

    public void svuotaOccorrenze() {
        for (int i=0; i<12; i++) {
            occorrenzeBit[i] = 0;
        }
    }

    public void segnaOccorrenze(String input) {
        char[] inputVector = new char[12];
        inputVector = input.toCharArray();

        for (int i = 0; i < 12; i++) {
            if (Character.getNumericValue(inputVector[i]) == 1) {
                occorrenzeBit[i]++;
            }
        }

    }

    public int getOccorrenzeBit(int i) {
        return occorrenzeBit[i];
    }
}
