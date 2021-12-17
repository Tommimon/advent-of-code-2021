package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class OperationPacket implements Packet{

    private final List<String> subBits = new ArrayList<>();
    private final String bits;
    private final int version, id;

    public OperationPacket(String bits) throws PacketNotOperationException{

        id = Integer.parseInt(bits.substring(3, 6), 2);
        if(id == 4) throw new PacketNotOperationException();

        version = Integer.parseInt(bits.substring(0, 3), 2);
        int lengthTypeID = bits.charAt(6) - '0';

        if(lengthTypeID == 0){
            int totalLength = Integer.parseInt(bits.substring(7, 22), 2);
            this.bits = bits.substring(0, 22 + totalLength);
            String nextBits = this.bits.substring(22);

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

            this.bits = bits.substring(0, 18 + totalLength);
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

    public List<String> getSubBits(){
        return new ArrayList<>(subBits);
    }

    @Override
    public long getValue() {
        long result = 0;
        List<Packet> subPackets = new ArrayList<>();

        for(String sBits: subBits){
            Packet packs = PacketManager.getPacket(sBits);
            subPackets.add(packs);
        }

        if(id == 0){
            for(Packet p : subPackets)
                result += p.getValue();

        } else if(id == 1){
            result = 1;
            for(Packet p : subPackets)
                result *= p.getValue();

        } else if(id == 2){
            result = subPackets.get(0).getValue();
            for(Packet p : subPackets)
                result = Math.min(result, p.getValue());

        } else if(id == 3){
            result = subPackets.get(0).getValue();
            for(Packet p : subPackets)
                result = Math.max(result, p.getValue());

        } else if(id == 5){
            result = subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1 : 0;

        } else if(id == 6){
            result = subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1 : 0;

        } else if(id == 7){
            result = subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1 : 0;

        }
        
        return result;
    }
}
