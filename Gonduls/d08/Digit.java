package Gonduls.d08;

import java.util.*;

public enum Digit {
    zero("abcefg", 0), one("cf", 1), two("acdeg", 2), three("acdfg", 3), four("bcdf", 4),
    five("abdfg", 5), six("abdefg", 6), seven("acf", 7), eight("abcdefg", 8), nine("abcdfg", 9);

    private final String letters;
    private final int val;
    private HashMap<Integer, Character> conversion = new HashMap<>();

    Digit(String letters, int val){
        this.letters = letters;
        this.val = val;
    }

    public void modifyConversion(char from, char to){
        conversion.put(convertChar(from), to);
    }

    public int convertChar(char letter){
        return (letter - 'a');
    }

    public int fullConvert(String string) throws ConversionIncompleteException{
        if(conversion.size() < 7)
            throw(new ConversionIncompleteException());
        String number = string.chars()
                .map(c -> convertChar(conversion.get(convertChar((char) c))))
                .toString();

        for(int i = 0; i< 7; i++){
            if(number.length() == (Digit.values()[i][0].letters.length()))
        }
    }
}
