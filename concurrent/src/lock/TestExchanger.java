package lock;

import java.util.concurrent.Exchanger;

public class TestExchanger {
    static Exchanger<String> e = new Exchanger<>();
    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";
            try {
                s = e.exchange(s);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        new Thread(()->{
            String s = "T2";
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                s = e.exchange(s);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }
}
