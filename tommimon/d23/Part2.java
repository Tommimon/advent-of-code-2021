package tommimon.d23;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Part2 {
    static Integer[] allowed = {0, 1, 3, 5, 7, 9, 10};
    static Integer[] forbidden = {2, 4, 6, 8};

    static void fillRow(Character[][] rooms, String str, int i) {
        str = str.substring(3, 10);
        Character[] nums = Arrays.stream(str.split("#")).map(s->s.toCharArray()[0]).toArray(Character[]::new);
        for (int j = 0; j < rooms.length; j++) {
            Character[] r = rooms[j];
            r[i] = nums[j];
        }
    }

    static boolean correct(Character[][] rooms, int i, int j) {
        if(rooms[j][i] == '.')
            return false;
        if(rooms[j][i] - 'A' != j)
            return false;
        if(i < 3)
            return correct(rooms, i+1, j);
        return true;
    }

    static boolean free(Character[][] rooms, int i, int j) {
        if(i == 0)
            return true;
        return rooms[j][0] == '.' && free(rooms, i-1, j);
    }

    static boolean connected(Character[] hallway, int start, int end) {
        int dir = -1; // going from end to start
        if(end < start)
            dir = 1;
        for(int i = end; i != start; i+=dir) {
            if(hallway[i] != '.')
                return false;
        }
        return true;
    }

    static int fuel(int steps, char c) {
        return (int) Math.pow(10, c - 'A') * steps;
    }

    static int minFuel(Character[][] rooms, Character[] hallway, int fuel, int depth) {
        for (int i = 0; i < hallway.length; i++) {
            if (hallway[i] != '.') {
                int destCol = hallway[i] - 'A';
                if (connected(hallway, i, forbidden[destCol])) {
                    for(int row = 3; row >= 0; row--) {
                        if(rooms[destCol][row] != hallway[i])
                            if(rooms[destCol][row] == '.') {
                                if(free(rooms, row, destCol)) {
                                    fuel += fuel(Math.abs(forbidden[destCol]-i)+1+row, hallway[i]);
                                    rooms[destCol][row] = hallway[i];
                                    hallway[i] = '.';
                                }
                            }
                            else
                                break;
                    }
                }
            }
        }
        ArrayList<Integer> options = new ArrayList<>();
        for (int col = 0; col < rooms.length; col++) {
            if(depth == 1 && col == 1)
                System.out.print("a");
            for (int row = 0; row < 4; row++) {
                if (rooms[col][row] != '.' && !correct(rooms, row, col) && free(rooms, row, col)) {
                    for (int i: allowed) {
                        int startPos = forbidden[col];
                        if (connected(hallway, startPos, i)) {
                            Character[][] roomCopy = Arrays.stream(rooms).map(c -> Arrays.copyOf(c, c.length)).toArray(Character[][]::new);
                            Character[] hallwayCopy = Arrays.copyOf(hallway, hallway.length);
                            int newFuel = fuel;
                            hallwayCopy[i] = roomCopy[col][row];
                            roomCopy[col][row] = '.';
                            int d = fuel(Math.abs(startPos-i)+1+row, hallwayCopy[i]);
                            newFuel += d;
                            options.add(minFuel(roomCopy, hallwayCopy, newFuel, depth+1));
                        }
                    }
                }
            }
        }
        if (options.size() > 0) {
            return options.stream().min(Integer::compareTo).get();
        }
        else {
            boolean correct = true;
            for(int j = 0; j < forbidden.length; j++) {
                if (rooms[j][0] != 'A'+j || rooms[j][1] != 'A'+j || rooms[j][2] != 'A'+j || rooms[j][3] != 'A'+j) {
                    correct = false;
                    break;
                }
            }
            if (correct) {
                return fuel;
            }
            else
                return 999999999;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get("tommimon/d23/input"));
        Vector<String> lines = new Vector<>(Arrays.asList(br.lines().filter(s->!s.isBlank()).toArray(String[]::new)));
        lines.insertElementAt(new String("  #D#C#B#A#"), 3);
        lines.insertElementAt(new String("  #D#B#A#C#"), 4);

        Character[][] rooms = new Character[4][4];
        for(int i = 0; i < 4; i++) {
            fillRow(rooms, lines.get(i+2), i);
        }
        Character[] hallway = new Character[11];
        Arrays.fill(hallway, '.');

        System.out.println(minFuel(rooms, hallway, 0, 0));
    }
}
