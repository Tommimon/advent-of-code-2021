package Gonduls.d16;

public class PacketNotOperationException extends Exception{
    public PacketNotOperationException(){
        super("ID is 4, no operation can be done");
    }
}
