package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class OperationPacket extends Packet{

    private final List<String> subPackets = new ArrayList<>();

    public OperationPacket(String bits) throws PacketNotOperationException{
        super(bits);
        if(super.getId() == 4) throw new PacketNotOperationException();

        int lengthTypeID = bits.charAt(6) - '0';

        if(lengthTypeID == 0){
            int totalLength = Integer.parseInt(bits.substring(7, 22), 2);
            super.bits = bits.substring(0, 22 + totalLength);

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
}
