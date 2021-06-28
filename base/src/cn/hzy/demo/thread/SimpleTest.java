package cn.hzy.demo.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SimpleTest {

    public static void main(String[] args) {
//
        final BooleanLock booleanLock = new BooleanLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    booleanLock.lock(8000);
                    int workDuration = new Random().nextInt(15);
                    System.out.println(Thread.currentThread().getName()+"工作了"+workDuration+"s");
                    TimeUnit.SECONDS.sleep(workDuration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } finally {
                    booleanLock.unlock();
                }
            }
        };
        Thread t1 = new Thread(runnable,"t1");
        Thread t2 = new Thread(runnable,"t2");
        Thread t3 = new Thread(runnable,"t3");

        t1.start();
        t2.start();
        t3.start();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t3.interrupt();
//        IntStream.range(0,3).mapToObj(i -> new Thread(runnable,"t"+i)).forEach(item->item.start());
    }
}
class SimpleRunnable implements Runnable{
    @Override
    public void run() {
        synchronized(SimpleTest.class){
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
