package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class LiteralPacket extends Packet{

    private final long value;

    public LiteralPacket(String bits) throws PacketNotLiteralException{
        super(bits);
        if(super.id != 4) throw new PacketNotLiteralException();

        int i = 6;
        while(bits.charAt(i) == '1')
            i += 5;

        super.bits = bits.substring(0, i+5);

        // calculate value
        String PacketBits = super.bits.substring(6);
        StringBuilder valueBits = new StringBuilder();

        // turns out StringBuilders are very versatile
        while(PacketBits.charAt(0) == '1'){
            valueBits.append(PacketBits, 1, 5);
            PacketBits = PacketBits.substring(5);
        }

        valueBits.append(PacketBits, 1,5);
        value = Long.parseLong(valueBits.toString(), 2);
    }

    public long getValue(){
        return value;
    }

    public List<String> getSubBits(){
        List<String> ans = new ArrayList<>();
        ans.add(bits);
        return ans;
    }
}
