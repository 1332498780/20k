package designpattern.behavior.chainofresponsibility.list;

public class HandleA implements IHandle{
    @Override
    public boolean handle() {
        boolean result = false;
        // process something
        System.out.println("handleA process something");
        return result;
    }
}
