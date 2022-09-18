package designpattern.behavior.statusmachine;

public enum State {

    SMALL(0),
    SUPER(1),
    FIRE(2),
    CAPE(3),
    ;
    private final int value;

    private State(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }
}
