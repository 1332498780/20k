package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Object;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    static volatile int counter = 0;
    public static void main(String[] args) throws Exception {
//        Chopstick c1 = new Chopstick("c1");
//        Chopstick c2 = new Chopstick("c2");
//        Chopstick c3 = new Chopstick("c3");
//        Chopstick c4 = new Chopstick("c4");
//        Chopstick c5 = new Chopstick("c5");
//        Philosopher p1 = new Philosopher("p1", c1, c2);
//        Philosopher p2 = new Philosopher("p2", c2, c3);
//        Philosopher p3 = new Philosopher("p3", c3, c4);
//        Philosopher p4 = new Philosopher("p4", c4, c5);
//        Philosopher p5 = new Philosopher("p5", c5, c1);
//        p1.start();
//        p2.start();
//        p3.start();
//        p4.start();
//        p5.start();
        ReentrantLock lock = new ReentrantLock(false);
        lock.lock();
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }
// 1s 之后去争抢锁
        Thread.sleep(1000);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start...");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " running...");
            } finally {
                lock.unlock();
            }
        }, "强行插入").start();
        lock.unlock();
    }

    public static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("i = " + i);
                System.out.println("park...");
                LockSupport.park();
                System.out.println("打断状态：{}"+ Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}

class Chopstick extends ReentrantLock {
    String name;
    public Chopstick(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}

class Philosopher extends Thread {
    Chopstick left;
    Chopstick right;
    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }
    @Override
    public void run() {
        while (true) {
            // 尝试获得左手筷子
            if (left.tryLock()) {
                try {
                    // 尝试获得右手筷子
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }
    private void eat() {
        System.out.println(Thread.currentThread().getName() +  "eating...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class A {
    static {
        System.out.println("A init!");
    }
    public static final Integer value = 123;
}

class B  extends  A{
    static {
        System.out.println("B init!");
    }
}

interface C{

}
interface D{

}
