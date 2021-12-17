package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class LiteralPacket implements Packet{

    private final String bits;
    private final int version, id;

    public LiteralPacket(String bits) throws PacketNotLiteralException{

        if(Integer.parseInt(bits.substring(3, 6), 2) != 4) throw new PacketNotLiteralException();
        id = 4;
        version = Integer.parseInt(bits.substring(0, 3), 2);

        int i = 6;
        while(bits.charAt(i) == '1')
            i += 5;

        this.bits = bits.substring(0, i+5);
    }

    @Override
    public long getValue(){
        String PacketBits = bits.substring(6);
        StringBuilder bits = new StringBuilder();

        // turns out StringBuilders are very versatile
        while(PacketBits.charAt(0) == '1'){
            bits.append(PacketBits, 1, 5);
            PacketBits = PacketBits.substring(5);

        }
        bits.append(PacketBits, 1,5);

        return Long.parseLong(bits.toString(), 2);
    }

    @Override
    public int getLength(){
        return bits.length();
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getBits() {
        return bits;
    }

    @Override
    public List<String> getSubBits(){
        List<String> ans = new ArrayList<>();
        ans.add(bits);
        return ans;
    }
}
