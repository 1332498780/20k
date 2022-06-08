package nolock;

public class TestVolatile {
    static volatile int a = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                a++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println(a);
            }
        }, "t1");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
