package cn.hzy.demo.thread;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.sound.midi.Track;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ReentryLock {

    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args){
//        Thread t1 = new Thread(new ReentryThread());
        Thread t2 = new Thread(new synReentry());
//        t1.start();
        t2.start();
    }

    static class ReentryThread implements Runnable{
        @Override
        public void run() {
            try {
                reentrantLock.lock();
//                Random random = new Random();
//                int res = random.nextInt(10);
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            reentrantLock.lock();
                            System.out.println("1");
                            reentrantLock.unlock();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            reentrantLock.lock();
                            System.out.println("2");
                            reentrantLock.unlock();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                t1.start();
                t2.start();
//                t1.join();
//                t2.join();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                System.out.println("第一次锁释放");
            }
        }
    }

    static class synReentry implements Runnable{
        @Override
        public void run() {
            synchronized (reentrantLock){
                while (true){
                    System.out.print("synchronized");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}
