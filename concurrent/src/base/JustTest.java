package base;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public class JustTest {
    public static void main(String[] args) {
        Object o = new Object();
        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();
    }
}
