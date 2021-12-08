package john_galt_10.d08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entry {
    private List<Character> caratteri = new ArrayList<>();
    private int corrispondeA = -1;

    public Entry() {

    }
    public void ordineAlf() {
        Collections.sort(caratteri, (c1,c2) -> {
            if (c1 > c2) return 1;
            else if (c1 < c2) return -1;
            else return 0;
        });
    }
    public void aggiungiEntry(String in) {
        switch (in.length()) {
            case 2:
                corrispondeA = 1;
                break;
            case 3:
                corrispondeA = 7;
                break;
            case 4:
                corrispondeA = 4;
                break;
            case 7:
                corrispondeA = 8;
                break;
        }

        for (char c : in.toCharArray()) {
            caratteri.add(c);
        }
    }

    public void remove(char c) {
        if (caratteri.contains(Character.valueOf(c)))
                    caratteri.remove(Character.valueOf(c));
        else return;
    }

    //controlla se tutti i caratteri della più corta(cars) sono nella più grande
    public boolean comprende(Entry cars) {
        boolean ris = true;
        for (char c : cars.getCaratteri()) {
            if (!caratteri.contains(c)) ris = false;
        }
        return ris;
    }

    public void setCorrispondeA(int corrispondeA) {
        this.corrispondeA = corrispondeA;
    }

    public int getLength() {
        return caratteri.size();
    }

    public int getCorrispondeA() {
        return corrispondeA;
    }

    public List<Character> getCaratteri() {
        return caratteri;
    }
    public boolean contiene(char c) {
        for (char cc : caratteri) {
            if (cc == c) return true;
        }
        return false;
    }

    //dati due codifiche che differiscono di un carattere restituisce quel carattere (da chiamare sulla più lunga)
    public char diff(Entry e) {
        for (char c : caratteri) {
            if (!e.getCaratteri().contains(c)) return c;
        }
        return 'z';
    }

    public void print() {
        for (char c : caratteri) {
            System.out.print(c);
        }
        System.out.print("         ");
        System.out.print(corrispondeA);
        System.out.print("\n");
    }

    public boolean uguali(List<Character> l) {
        boolean ris = true;
        if (l.size() != caratteri.size()) return false;
        for (char c : l) {
            if (!caratteri.contains(c)) ris = false;
        }
        return ris;
    }
}
