package Gonduls.d16;

import java.util.List;

public interface Packet {
    int getId();
    int getVersion();
    int getLength();
    String getBits();
    List<String> getSubBits();
    long getValue();
}
