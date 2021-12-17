package Gonduls.d16;

import java.util.List;

public abstract class Packet {
    String bits; // "bits" is not final because its length is going to be changed by the subclasses
    final int id, version;

    public Packet(String bits){
        this.bits = bits;
        id = Integer.parseInt(bits.substring(3, 6), 2);
        version = Integer.parseInt(bits.substring(0, 3), 2);
    }

    int getId(){
        return id;
    }

    int getVersion(){
        return version;
    }

    int getLength(){
        return bits.length();
    }

    String getBits() {
        return bits;
    }

    abstract List<String> getSubBits();
    abstract long getValue();
}
