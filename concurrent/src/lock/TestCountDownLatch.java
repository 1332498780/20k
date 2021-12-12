package lock;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        CountDownLatch l = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                System.out.println("*****");
                System.out.println(l.getCount());
                l.countDown();
            }).start();
        }
        try {
            l.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
