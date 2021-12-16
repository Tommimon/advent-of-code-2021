package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class OperationPacket implements Packet{

    private final List<String> subPackets = new ArrayList<>();
    private String bits;
    private final int version, id;

    public OperationPacket(String bits) throws PacketNotOperationException{
        id = Integer.parseInt(bits.substring(3, 6), 2);
        if(id == 4) throw new PacketNotOperationException();
        version = Integer.parseInt(bits.substring(0, 3), 2);

        int lengthTypeID = bits.charAt(6) - '0';

        if(lengthTypeID == 0){
            int totalLength = Integer.parseInt(bits.substring(7, 22), 2);
            this.bits = bits.substring(0, 22 + totalLength);

            String nextBits = bits.substring(22);

            while(nextBits.length() == 0){
                Packet packet;

                try{
                    packet = new LiteralPacket(nextBits);
                } catch(PacketNotLiteralException e){
                    packet = new OperationPacket(nextBits);
                }
                subPackets.add(bits.substring(0, packet.getLength()));

                nextBits = nextBits.substring(packet.getLength());
            }
        }


    }

    public int getId(){
        return id;
    }

    public int getVersion(){
        return version;
    }

    public int getLength(){
        return bits.length();
    }

    public String getBits(){
        return bits;
    }

    public List<String> getSubPackets(){
        return new ArrayList<>(subPackets);
    }

}
