package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(21, () -> {
            System.out.println("满人发车");
        });
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cb.await();
                    System.out.println(Thread.currentThread().getName() + "*** ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
