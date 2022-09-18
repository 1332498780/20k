package designpattern.behavior.chainofresponsibility.linkedlist;

public class HandleB extends Handle{

    @Override
    public boolean doHandle() {
        boolean result = false;
        // process something
        System.out.println("handleB process something");
        return result;
    }
}
