package tommimon.d16;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class Day16 {
    static int sum = 0;

    static int arrayToInt(Integer[] bits) {
        int n = 0;
        for(Integer b: bits) {
            n *= 2;
            n += b;
        }
        return n;
    }

    static long applyFunc(Vector<Long> nums, int func) {
        return switch (func) {
            case 0 -> //sum
                    nums.stream().reduce(0L, Long::sum);
            case 1 -> //product
                    nums.stream().reduce(1L, (a, b) -> a * b);
            case 2 -> //min
                    nums.stream().min(Long::compareTo).get();
            case 3 -> //max
                    nums.stream().max(Long::compareTo).get();
            case 5 -> //grater than
                    nums.get(0) > nums.get(1) ? 1 : 0;
            case 6 -> //less than
                    nums.get(0) < nums.get(1) ? 1 : 0;
            case 7 -> //equal to
                    Objects.equals(nums.get(0), nums.get(1)) ? 1 : 0;
            default -> 0;
        };
    }

    static Long[] versionCount(Integer[] bits) {
        sum += arrayToInt(Arrays.copyOfRange(bits, 0, 3));
        int type = arrayToInt(Arrays.copyOfRange(bits, 3, 6));
        int pos;
        if (type == 4) {
            pos = 1;
            long res = 0L;
            do {
                pos += 5;
                res *= 16;
                res += arrayToInt(Arrays.copyOfRange(bits, pos + 1, pos + 5));
            } while (bits[pos] == 1);
            return new Long[]{pos+5L, res};
        }
        else {
            Vector<Long> subPackage = new Vector<>();
            int lenType = bits[6];
            if (lenType == 1) {
                int len = arrayToInt(Arrays.copyOfRange(bits, 7, 18));
                pos = 18;
                for (int i = 0; i < len; i++) {
                    Long[] response = versionCount(Arrays.copyOfRange(bits, pos, bits.length));
                    pos += response[0];
                    subPackage.add(response[1]);
                }
            }
            else {
                int len = arrayToInt(Arrays.copyOfRange(bits, 7, 22));
                pos = 22;
                while (pos < len + 22) {
                    Long[] response = versionCount(Arrays.copyOfRange(bits, pos, bits.length));
                    pos += response[0];
                    subPackage.add(response[1]);
                }
            }
            Long[] longs = {(long) pos, applyFunc(subPackage, type)};
            return longs;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d16/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        char[] letters = lines.get(0).toCharArray();
        Vector<Integer> bitsVec = new Vector<>();
        for (char l : letters) {
            String s = Integer.toBinaryString(Integer.parseInt(""+l, 16));
            for (int i = 0; i < 4; i++) {
                if (i < 4-s.length())
                    bitsVec.add(0);
                else
                    bitsVec.add(Integer.parseInt(""+s.toCharArray()[i-4+s.length()]));
            }
        }
        Integer[] bits = bitsVec.toArray(Integer[]::new);
        Long[] res = versionCount(bits);
        System.out.println(sum);
        System.out.println(res[1]);
    }
}
