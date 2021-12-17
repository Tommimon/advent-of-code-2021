package Gonduls.d16;

public class PacketManager {

    private final Packet packet;


    static Packet getPacket(String bits){
        // Not exactly how you are supposed to use Exceptions, but it works. Fairly used function
        Packet packet = null;
        try{
            packet = new OperationPacket(bits);
        } catch (PacketNotOperationException e){
            try{
                packet = new LiteralPacket(bits);
            }
            catch (PacketNotLiteralException ex){
                System.out.println("Invalid packet: not an operation nor a literal, check input");
            }
        }

        assert packet != null;
        return packet;
    }

    public PacketManager(String bits){
        this.packet = getPacket(bits);
    }

    public int sumVersion(){
        return sumVersion(packet);
    }

    private int sumVersion(Packet packet){
        int answer = 0;

        if(packet.getId() == 4)
            return packet.getVersion();

        for(String bits : packet.getSubBits()) {
            Packet current = getPacket(bits);
            answer += sumVersion(current);
        }
        return answer + packet.getVersion();
    }

    public long getValue(){
        return packet.getValue();
    }

}
