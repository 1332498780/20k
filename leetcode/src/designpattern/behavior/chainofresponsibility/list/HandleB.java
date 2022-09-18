package designpattern.behavior.chainofresponsibility.list;

public class HandleB implements IHandle{
    @Override
    public boolean handle() {
        boolean result = false;
        // process something
        System.out.println("handleB process something");
        return result;
    }
}
