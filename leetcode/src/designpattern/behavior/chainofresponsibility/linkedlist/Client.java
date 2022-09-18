package designpattern.behavior.chainofresponsibility.linkedlist;

public class Client {
    public static void main(String[] args) {
        ChainHandle chainHandle = new ChainHandle();
        HandleA handleA = new HandleA();
        HandleB handleB = new HandleB();
        chainHandle.addHandle(handleA);
        chainHandle.addHandle(handleB);

        chainHandle.handle();
    }
}
