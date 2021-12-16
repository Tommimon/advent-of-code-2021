package Gonduls.d16;

public class PacketNotLiteralException extends Exception{
    public PacketNotLiteralException(){
        super("ID not 4, no literal value can be given");
    }
}
