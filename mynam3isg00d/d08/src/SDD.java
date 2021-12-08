package mynam3isg00d.d08.src;

import java.util.Arrays;

public class SDD {
    String digits[] = new String[10];

    SDD() {
        Arrays.fill(digits, "");
    }

    public boolean checkDecoded() {
        for(String s : digits) if (s.isEmpty()) return true;
        return false;
    }

    public void print() {
        System.out.println("Decoding completed here it is chief: ");
        for(int i=0; i<this.digits.length; i++) {
            System.out.println("[" + i + "]: " + this.digits[i]);
        }
        System.out.println("\n");

    }

    public String find(String s) {
        for(int i=0; i<digits.length; i++) {
            if (digits[i].compareTo(s) == 0) return String.valueOf(i);
        }
        return "#";
    }
}
