package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public interface Packet {
    /*

    Packet (String bits){
        this.bits = bits;
        version = Integer.parseInt(bits.substring(0, 3), 2);
        id = Integer.parseInt(bits.substring(3, 6), 2);
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getBits() {
        return bits;
    }

    public List<String> getSubPackets(){
        List<String> answer = new ArrayList<>();
        answer.add(bits);
        return answer;

    }*/

    public int getId();
    public int getVersion();
    public int getLength();
    public String getBits();
    public List<String> getSubPackets();


}
