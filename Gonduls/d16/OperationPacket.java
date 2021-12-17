package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class OperationPacket extends Packet{

    private final List<String> subBits = new ArrayList<>();

    public OperationPacket(String bits) throws PacketNotOperationException{
        super(bits);
        if(super.id == 4) throw new PacketNotOperationException();

        int lengthTypeID = bits.charAt(6) - '0';

        // adding subBits to the list following the lengthTypeID
        if(lengthTypeID == 0){
            int totalLength = Integer.parseInt(bits.substring(7, 22), 2);
            super.bits = bits.substring(0, 22 + totalLength);
            String nextBits = super.bits.substring(22);

            // I only know if there are still packets left only by checking if I have bits left or not
            while(nextBits.length() > 0){
                Packet packet = PacketManager.getPacket(nextBits);
                subBits.add(packet.getBits());
                nextBits = nextBits.substring(packet.getLength());
            }
        }
        else{
            int numOfPacketsLeft = Integer.parseInt(bits.substring(7, 18), 2);
            int totalLength = 0;
            String nextBits = bits.substring(18);

            // I need to keep track of this current packet length:
            // this information can only be calculated through the sum of all subPackets lengths
            while(numOfPacketsLeft > 0){
                Packet packet = PacketManager.getPacket(nextBits);

                subBits.add(packet.getBits());
                nextBits = nextBits.substring(packet.getLength());

                totalLength += packet.getLength();
                numOfPacketsLeft --;
            }

            super.bits = bits.substring(0, 18 + totalLength);
        }
    }

    public List<String> getSubBits(){
        return new ArrayList<>(subBits);
    }

    public long getValue() {
        long result = 0;
        List<Packet> subPackets = new ArrayList<>();

        for(String sBits: subBits){
            Packet packs = PacketManager.getPacket(sBits);
            subPackets.add(packs);
        }

        if(super.id == 0){
            for(Packet p : subPackets)
                result += p.getValue();

        } else if(super.id == 1){
            result = 1;
            for(Packet p : subPackets)
                result *= p.getValue();

        } else if(super.id == 2){
            result = subPackets.get(0).getValue();
            for(Packet p : subPackets)
                result = Math.min(result, p.getValue());

        } else if(super.id == 3){
            result = subPackets.get(0).getValue();
            for(Packet p : subPackets)
                result = Math.max(result, p.getValue());

        } else if(super.id == 5){
            result = subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1 : 0;

        } else if(super.id == 6){
            result = subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1 : 0;

        } else if(super.id == 7){
            result = subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1 : 0;

        }
        
        return result;
    }
}
