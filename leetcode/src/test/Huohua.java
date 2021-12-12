package test;

public class Huohua {
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(10);
        new Thread(() -> {
            try {
                fooBar.foo(new Thread(() -> {
                    System.out.println("foo");
                }));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.foo(new Thread(() -> {
                    System.out.println("bar");
                }));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
class FooBar {
    private Object o = new Object();
    private int n;
    public FooBar(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            synchronized (o) {
                printFoo.run();
                o.notify();
                o.wait();
            }
        }
    }
    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            synchronized (o) {
                printBar.run();
                o.notify();
                o.wait();
            }
        }
    }
}
