package Gonduls.d16;

import java.util.ArrayList;
import java.util.List;

public class PacketManager {

    final Packet packet;

    public PacketManager(String packet){
        this.packet = new Packet(packet);
    }

    public int getVersion(){
        return packet.getVersion();
    }

    public int getId() {
        return packet.getId();
    }

    public List<String> getSubPackets(){
        return getSubPackets(this);
    }

    public List<String> getSubPackets(PacketManager packetManager){
        if(packetManager.id == 4) return null;

        List<String> subPackets = new ArrayList<>();
        int lenghtTypeId = packetManager.packet.getPacket().charAt(6) - '0';

        if(lenghtTypeId == 1){
            int packetsNum = Integer.parseInt(packetManager.packet.getPacket().substring(7, 18), 2);
            String Packetbits = packet.getPacket().substring(18);

            for(int i = 0; i< packetsNum; i++){

            }
        }
        return subPackets;
    }


}
