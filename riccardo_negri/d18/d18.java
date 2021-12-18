//
// Day 18 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 3884
// Second part solution: 4595
//

package riccardo_negri.d18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d18 {
    public static void main (String[] args) throws Exception {
        File f = new File("riccardo_negri/d18/input.txt");
        Scanner reader = null;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        Pair pair = null;
        boolean firstPair = true;
        List<String> listLines = new ArrayList<>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            listLines.add(line);

            // get new pair
            if (firstPair) {
                pair = acquireInput(line);
                //print(pair);
                firstPair = false;
            }
            else {
                Pair newPair = acquireInput(line);
                pair = new Pair(pair, newPair);
                //System.out.println("Before reduce: ");
                //print(pair);
                reduce(pair);
                //System.out.println();
                //System.out.println("After reduce: ");
                //print(pair);
            }
            //System.out.println();
            //System.out.println();
        }
        System.out.println(pair != null ? calculateMagnitude(pair) : "Got null");

        // largest possible magnitude as the result of a sum of two distinct pairs
        int maxMagnitude = 0;
        for (String l1 : listLines) {
            for (String l2 : listLines) {
                if (!l1.equals(l2)) {
                    Pair tempPair = new Pair(acquireInput(l1), acquireInput(l2));
                    reduce(tempPair);
                    int temp = calculateMagnitude(tempPair);
                    if (temp > maxMagnitude) {
                        maxMagnitude = temp;
                    }
                }
            }
        }
        System.out.println(maxMagnitude);
    }


    public static Pair acquireInput (String str) {
        if (!str.substring(1, str.length() - 1).contains("[")) {
            String[] split = str.substring(1, str.length() - 1).split(",");
            return new Pair(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        int counterOpenBrackets = 0;
        int middle = 0;
        for (int i = 0; i < str.length(); i++) { // find middle to call acquireInput on subpair
            if ('[' == str.charAt(i)) {
                counterOpenBrackets++;
            }
            else if (']' == str.charAt(i)) {
                counterOpenBrackets--;
            }
            if (',' == str.charAt(i) && counterOpenBrackets == 1) {
                middle = i;
                break;
            }
        }

        Object first;
        String substring = str.substring(1, middle);
        if (substring.contains(",")) {
            first = acquireInput(substring);
        }
        else {
            first = Integer.parseInt(substring);
        }
        Object second;
        substring = str.substring(middle + 1, str.length() - 1);
        if (substring.contains(",")) {
            second = acquireInput(substring);
        }
        else {
            second = Integer.parseInt(substring);
        }

        return new Pair(first, second);
    }

    // keeps doing explode and split
    public static void reduce (Pair pair) {
        while (true) {
            if (!(reduceByExplode(pair, 1)[0] == 1)) { // if it can't reduce by explode tries by split
                if (!reduceBySplit(pair)) { // if it can't reduce even by split ends reduce call
                    break;
                }
            }
        }
    }

    // specification of array of Integers:
    // position 0: something change (0 == no, 1 == yes)
    // position 1: value to propagate left (-1 if none)
    // position 2: value to propagate right (-1 if none)
    public static Integer[] reduceByExplode (Pair pair, int depth) {

        Integer[] toReturn;
        // base case
        if (depth == 5) {
            toReturn = new Integer[]{1, (Integer) pair.getFirst(), (Integer) pair.getSecond()};
            return toReturn;
        }
        // cause explosion and handle propagation of explosion
        else {
            assert (depth < 5);
            Integer[] result;
            if (pair.getFirst().getClass() == Pair.class) {
                result = reduceByExplode((Pair) pair.getFirst(), depth + 1);
                if (result[0] == 1) { // something happened, need to propagate before going on with other things
                    if (depth == 4) {
                        pair.setFirst(0);
                    }
                    // Propagate right
                    if (pair.getSecond().getClass() != Pair.class) {
                        pair.setSecond((Integer) pair.getSecond() + result[2]);
                    }
                    else {
                        propagateRight((Pair) pair.getSecond(), result[2]);
                    }
                    return new Integer[]{1, result[1], 0};

                }
            }
            if (pair.getSecond().getClass() == Pair.class) {
                result = reduceByExplode((Pair) pair.getSecond(), depth + 1);
                if (result[0] == 1) { // something happened, need to propagate before going on with other things
                    if (depth == 4) {
                        pair.setSecond(0);
                    }
                    // propagate left
                    if (pair.getFirst().getClass() != Pair.class) {
                        pair.setFirst((Integer) pair.getFirst() + result[1]);
                    }
                    else {
                        propagateLeft((Pair) pair.getFirst(), result[1]);
                    }
                    return new Integer[]{1, 0, result[2]};

                }
            }
            return new Integer[]{0};
        }
    }

    public static void propagateRight (Pair pair, int toAdd) {
        if (pair.getFirst().getClass() == Pair.class) {
            propagateRight((Pair) pair.getFirst(), toAdd);
        }
        else {
            pair.setFirst((Integer) pair.getFirst() + toAdd);
        }
    }

    public static void propagateLeft (Pair pair, int toAdd) {
        if (pair.getSecond().getClass() == Pair.class) {
            propagateLeft((Pair) pair.getSecond(), toAdd);
        }
        else {
            pair.setSecond((Integer) pair.getSecond() + toAdd);
        }
    }

    // returns true if something has changed
    public static boolean reduceBySplit (Pair pair) {
        if (pair.getFirst().getClass() == Pair.class) {
            if (reduceBySplit((Pair) pair.getFirst())) {
                return true;
            }
        }
        else {
            if ((Integer) pair.getFirst() >= 10) {
                int temp = (Integer) pair.getFirst();
                pair.setFirst(new Pair(temp / 2, temp / 2 + temp % 2));
                return true;
            }
        }

        if (pair.getSecond().getClass() == Pair.class) {
            return reduceBySplit((Pair) pair.getSecond());
        }
        else {
            if ((Integer) pair.getSecond() >= 10) {
                int temp = (Integer) pair.getSecond();
                pair.setSecond(new Pair(temp / 2, temp / 2 + temp % 2));
                return true;
            }
        }

        return false;
    }

    public static void print (Pair pair) {
        System.out.print("[");
        if (pair.getFirst().getClass() == Pair.class) {
            print((Pair) pair.getFirst());
        }
        else {
            System.out.print(pair.getFirst());
        }
        System.out.print(",");
        if (pair.getSecond().getClass() == Pair.class) {
            print((Pair) pair.getSecond());
        }
        else {
            System.out.print(pair.getSecond());
        }
        System.out.print("]");
    }

    public static int calculateMagnitude (Pair pair) {
        int result = 0;
        if (pair.getFirst().getClass() == Pair.class) {
            result += 3 * calculateMagnitude((Pair) pair.getFirst());
        }
        else {
            result += 3 * (Integer) pair.getFirst();
        }
        if (pair.getSecond().getClass() == Pair.class) {
            result += 2 * calculateMagnitude((Pair) pair.getSecond());
        }
        else {
            result += 2 * (Integer) pair.getSecond();
        }
        return result;
    }

}
