package designpattern.behavior.chainofresponsibility.linkedlist;

public class ChainHandle {

    private Handle head;
    private Handle tail;

    public void addHandle(Handle handle) {
        if (head == null) {
            head = handle;
            tail = handle;
            return;
        }
        tail.setNext(handle);
        tail = handle;
    }

    public void handle() {
        if (head != null) {
            head.handle();
        }
    }
}
