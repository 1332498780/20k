package designpattern.behavior.chainofresponsibility.linkedlist;

public abstract class Handle {
    private Handle next;
    public void handle() {
        boolean result = this.doHandle();
        if (!result && getNext() != null) {
            getNext().handle();
        }
    };

    public abstract boolean doHandle();

    public Handle getNext() {
        return next;
    }

    public void setNext(Handle next) {
        this.next = next;
    }
}
