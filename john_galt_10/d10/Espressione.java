package john_galt_10.d10;

import java.util.*;

public class Espressione {
    Stack<Character> pilaCaratteri;

    public Espressione() {
        pilaCaratteri = new Stack<>();
    }

    public int check(char c) {
        if (c == '(' || c == '[' || c == '{' || c == '<') {
            pilaCaratteri.push(c);
        } else {
            char cPop = (Character) pilaCaratteri.pop();
            //System.out.println("cc = " + cc + "    c = " + c);
            char cInverso = c;
            if (cPop == '(')  cInverso = ')';
            if (cPop == '[') cInverso = ']';
            if (cPop == '{') cInverso = '}';
            if (cPop == '<') cInverso = '>';

            if (c == cInverso) {
                System.out.println("Poppato " + cPop);
                return 0;
            }

            switch (c) {
                case ')':
                    return 3;
                case ']':
                    return 57;
                case '}':
                    return 1197;
                case '>':
                    return 25137;
            }
        }
        System.out.println("Aggiunto " + c);
        return 0;
    }

    public String stato() {
        if (pilaCaratteri.isEmpty()) return "vuota";
        return "incompleta";
    }
    public long punteggioMancanti() {
        long punteggio = 0;
        while (!pilaCaratteri.isEmpty()) {
            punteggio = punteggio*5;
            char x = pilaCaratteri.pop();
            switch (x) {
                case '(':
                    punteggio += 1;
                    break;
                case '[':
                    punteggio += 2;
                    break;
                case '{':
                    punteggio += 3;
                    break;
                case '<':
                    punteggio += 4;
                    break;
            }
        }
        return punteggio;
    }

    public String toString() {
        String ris = "";
        for (char c : pilaCaratteri) ris = ris + c;
        return ris;
    }
}
