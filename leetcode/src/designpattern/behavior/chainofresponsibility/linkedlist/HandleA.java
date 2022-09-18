package designpattern.behavior.chainofresponsibility.linkedlist;

public class HandleA extends Handle{

    @Override
    public boolean doHandle() {
        boolean result = false;
        // process something
        System.out.println("handleA process something");
        return result;
    }
}
