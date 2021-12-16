//
// Day 16 solution of Advent Of Code 2021 by Riccardo Negri
// First part solution: 847
// Second part solution: 333794664059
//

package riccardo_negri.d16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class d16 {
    static long INFINITY = Long.MAX_VALUE;

    public static void main (String[] args) throws Exception {
        File f = new File("riccardo_negri/d16/input.txt");
        Scanner reader = null;

        try {
            reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        String line = reader.nextLine();
        line = hexToBin(line);

        // result has in last place the index, second last place the sum of versions and in the other places the results/values
        List<Long> result = parse(line, INFINITY, true);
        System.out.println(result.get(1));
        System.out.println(result.get(0));
    }

    public static List<Long> parse (String s, long numberPacketsExpected, boolean isFirst) {
        long versionsSum = 0;
        long index = 0;
        int currNumPackets = 0;
        List<Long> resultToReturn = new ArrayList<>();
        while (index < s.length()) {
            long version = Integer.parseInt(s.substring((int) index, (int) (index + 3)), 2);
            versionsSum += version;
            // type 4: the packet is a literal value
            // type not4 --> check lengthTypeID: represent an operator that performs some calculation on one or more sub-packets contained within
            // if the length type ID is 0, then the next 15 bits are a number that represents the total length in bits of the sub-packets contained by this packet
            // if the length type ID is 1, then the next 11 bits are a number that represents the number of sub-packets immediately contained by this packet
            int typeID = Integer.parseInt(s.substring((int) index + 3, (int) index + 6), 2);

            if (typeID == 4) {
                index = index + 6;
                StringBuilder value = new StringBuilder();
                while (s.charAt((int) index) == '1') {
                    value.append(s, (int) index + 1, (int) index + 5);
                    index = index + 5;
                }
                value.append(s, (int) index + 1, (int) index + 5);
                resultToReturn.add(Long.parseLong(value.toString(), 2));
                index = index + 5;
            }
            else {
                int lengthTypeID = Integer.parseInt(s.charAt((int) index + 6) + "", 2);
                List<Long> result;
                if (lengthTypeID == 0) {
                    // next 15 bits are a number that represents the total length in bits of the sub-packets contained by this packet
                    int lengthSubPackets = Integer.parseInt(s.substring((int) index + 7, (int) index + 22), 2);
                    result = parse(s.substring((int) index + 22, (int) index + 22 + lengthSubPackets), INFINITY, false);
                    versionsSum += result.get(result.size() - 2);
                    index = index + 22 + lengthSubPackets;
                }
                else {
                    // next 11 bits are a number that represents the number of sub-packets immediately contained by this packet
                    int numberSubPackets = Integer.parseInt(s.substring((int) index + 7, (int) index + 18), 2);
                    result = parse(s.substring((int) index + 18), numberSubPackets, false);
                    versionsSum += result.get(result.size() - 2);
                    index += result.get(result.size() - 1) + 18;
                }
                if (typeID == 0) { // sum packets - their value is the sum of the values of their sub-packets
                    long sum = 0;
                    for (int i = 0; i < result.size() - 2; i++) {
                        sum += result.get(i);
                    }
                    resultToReturn.add(sum);
                }
                else if (typeID == 1) { // product packets - their value is the result of multiplying together the values of their sub-packets
                    long prod = 1;
                    for (int i = 0; i < result.size() - 2; i++) {
                        prod *= result.get(i);
                    }
                    resultToReturn.add(prod);
                }
                else if (typeID == 2) { // minimum packets - their value is the minimum of the values of their sub-packets
                    long min = INFINITY;
                    for (int i = 0; i < result.size() - 2; i++) {
                        if (result.get(i) < min) {
                            min = result.get(i);
                        }
                    }
                    resultToReturn.add(min);
                }
                else if (typeID == 3) { // maximum packets - their value is the maximum of the values of their sub-packets
                    long max = 0;
                    for (int i = 0; i < result.size() - 2; i++) {
                        if (result.get(i) > max) {
                            max = result.get(i);
                        }
                    }
                    resultToReturn.add(max);
                }
                else if (typeID == 5) { // greater than packets - their value is 1 if the value of the first sub-packet is greater than the value of the second sub-packet; otherwise, their value is 0
                    assert (result.size() == 4);
                    long value;
                    if (result.get(0) > result.get(1)) {
                        value = 1;
                    }
                    else {
                        value = 0;
                    }
                    resultToReturn.add(value);
                }
                else if (typeID == 6) { // less than packets - their value is 1 if the value of the first sub-packet is less than the value of the second sub-packet; otherwise, their value is 0
                    assert (result.size() == 4);
                    long value;
                    if (result.get(0) < result.get(1)) {
                        value = 1;
                    }
                    else {
                        value = 0;
                    }
                    resultToReturn.add(value);
                }
                else if (typeID == 7) { // equal to packets - their value is 1 if the value of the first sub-packet is equal to the value of the second sub-packet; otherwise, their value is 0
                    assert (result.size() == 4);
                    long value;
                    if (Objects.equals(result.get(0), result.get(1))) {
                        value = 1;
                    }
                    else {
                        value = 0;
                    }
                    resultToReturn.add(value);
                }
                else {
                    System.out.println("Unknown typeID");
                }
            }
            currNumPackets++;
            if (currNumPackets >= numberPacketsExpected || isFirst) {
                resultToReturn.add(versionsSum);
                resultToReturn.add(index);
                return resultToReturn;
            }
        }

        resultToReturn.add(versionsSum);
        resultToReturn.add(index);
        return resultToReturn;
    }

    public static String hexToBin (String s) {
        StringBuilder binStr = new StringBuilder();
        HashMap<Character, String> hashMap = new HashMap<>();

        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        for (int i = 0; i < s.length(); i++) {
            binStr.append(hashMap.get(s.charAt(i)));
        }

        return binStr.toString();
    }
}
